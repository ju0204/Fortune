package likelion.team6th.fortune.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import likelion.team6th.fortune.dto.UserDTO;
import likelion.team6th.fortune.util.AESUtil;
import likelion.team6th.fortune.util.PropertiesGetter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import likelion.team6th.fortune.dto.TarotDTO;
import likelion.team6th.fortune.dto.ZodiacAdminDTO;
import likelion.team6th.fortune.dto.ZodiacDTO;
import likelion.team6th.fortune.service.CommonService;
import likelion.team6th.fortune.service.PagingService;
import lombok.RequiredArgsConstructor;

import static likelion.team6th.fortune.util.CreateQRcode.getQRCodeImage;

@Controller
@RequiredArgsConstructor
public class CommonController {


	private final AESUtil aesUtil;
	private final CommonService commonService;
	private final PagingService pagingService;
	private final PropertiesGetter properties;

	@PostMapping("/fortune")
	public String drawFortune(UserDTO userdto,
							  Model model) throws Exception {

		int year = userdto.getYear();
		String name = userdto.getName();

		Map<String, String> userInfo = new HashMap<>();
		userInfo.put("year", String.valueOf(year));
		userInfo.put("name", name);


		String plainValues = "fortune?name?" + name + "?year?" + year;
		String encryptValues = aesUtil.encrypt(plainValues);
		String encodeValues = URLEncoder.encode(encryptValues, StandardCharsets.UTF_8.toString());
		String address = "http://"+ properties.getServerAddress() +"/share?" + encodeValues;
		String qrcode = getQRCodeImage(address,200,200);

		TarotDTO tarotDTO = commonService.getTarot(year, name);
		ZodiacDTO zodiacDTO = commonService.getZodiac(year, name);

		model.addAttribute("tarot", tarotDTO);
      	model.addAttribute("zodiac", zodiacDTO);
		model.addAttribute("qrcode", qrcode);
		model.addAttribute("address", address);
		model.addAttribute("userInfo", userInfo);

	    return "result"; // JSON 응답으로 변환됨
	}

	@GetMapping("/admin/list")
	public String getAllFortune(@PageableDefault(page = 0, size = 8) Pageable pageable,
								@RequestParam(defaultValue = "타로") String category,
								ModelMap map) {

		Map<String, Object> response = new HashMap<>();

		if ("타로".equals(category)) {
			Page<TarotDTO> tarotList = commonService.getAllTarots(pageable);
			List<Integer> tarotPageNumbers  = pagingService.getPageNumbers(pageable.getPageNumber(),
					tarotList.getTotalPages());
			response.put("tarotList", tarotList);
			response.put("tarotPage", tarotPageNumbers);
		} else if ("띠".equals(category)) {
			Page<ZodiacAdminDTO> zodiacList = commonService.getAllZodiacs(pageable);
			List<Integer> zodiacPageNumbers  = pagingService.getPageNumbers(pageable.getPageNumber(),
					zodiacList.getTotalPages());
			response.put("zodiacList", zodiacList);
			response.put("zodiacPage", zodiacPageNumbers);
		}

		map.addAllAttributes(response);
		map.addAttribute("category", category);

		return "admin/admin-manage";
	}

	@GetMapping("/share")
	public String cryptTests(HttpServletRequest request,
							 Model model) throws Exception{
		String decodeValues = URLDecoder.decode(request.getQueryString(),
				StandardCharsets.UTF_8.toString());
		return "redirect:/fortune?" + decodeValues;
	}

	@GetMapping("/fortune")
	public String fortuneFromRedirect(HttpServletRequest request,
									  Model model) throws Exception {

		String decryptValues = aesUtil.decrypt(request.getQueryString());

		String name = decryptValues.split("\\?")[2];
		int year = Integer.parseInt(decryptValues.split("\\?")[4]);

		Map<String, String> userInfo = new HashMap<>();
		userInfo.put("year", String.valueOf(year));
		userInfo.put("name", name);

		String plainValues = "fortune?name?" + name + "?year?" + year;
		String encryptValues = aesUtil.encrypt(plainValues);
		String encodeValues = URLEncoder.encode(encryptValues, StandardCharsets.UTF_8.toString());
		String address = "http://"+ properties.getServerAddress() +"/share?" + encodeValues;
		String qrcode = getQRCodeImage(address,200,200);

		TarotDTO tarotDTO = commonService.getTarot(year, name);
		ZodiacDTO zodiacDTO = commonService.getZodiac(year, name);

		model.addAttribute("tarot", tarotDTO);
		model.addAttribute("zodiac", zodiacDTO);
		model.addAttribute("qrcode", qrcode);
		model.addAttribute("address", address);
		model.addAttribute("userInfo", userInfo);

		return "result";
	}

}
