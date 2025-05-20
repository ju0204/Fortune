package likelion.team6th.fortune.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import likelion.team6th.fortune.dto.TarotDTO;
import likelion.team6th.fortune.dto.ZodiacAdminDTO;
import likelion.team6th.fortune.dto.ZodiacDTO;
import likelion.team6th.fortune.service.CommonService;
import likelion.team6th.fortune.service.PagingService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CommonController {
	
	private final CommonService commonService;
	private final PagingService pagingService;
	
	@PostMapping("/fortune")
	public Map<String, Object> drawFortune(@RequestBody Map<String, Object> request) {
		
		int year = Integer.parseInt(request.get("year").toString());
		String name = request.get("name").toString();
		
		
		TarotDTO tarotDTO = commonService.getTarot(year, name);
		ZodiacDTO zodiacDTO = commonService.getZodiac(year, name);
		
		// Model 객체에 담기
//		model.addAttribute("tarot", tarot);
//      model.addAttribute("zodiac", zodiacDTO);
		
		Map<String, Object> response = new HashMap<>();
	    response.put("tarot", tarotDTO);
	    response.put("zodiac", zodiacDTO);

	    return response; // JSON 응답으로 변환됨
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

}
