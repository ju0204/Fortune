package likelion.team6th.fortune.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.team6th.fortune.config.auth.PrincipalDetails;
import likelion.team6th.fortune.util.AESUtil;
import likelion.team6th.fortune.util.PropertiesGetter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.net.URLDecoder;
import static likelion.team6th.fortune.util.CreateQRcode.getQRCodeImage;




@Controller
@PropertySource("classpath:admin.properties")
@AllArgsConstructor
public class MainContoller {

    @Autowired
    private AESUtil aesUtil;


    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String logInPage() {
        return "login";
    }

    @GetMapping("/fail")
    public String failPage() {
        return "fail";
    }

    @GetMapping("/admin")
    public String adminPage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        return "admin";
    }

    @GetMapping("/qrcode")
    public String qrcodePage(Model model) throws Exception{

        //QRcode 생성은 이렇게 해주세요
        String year = "1999";
        String name = "문지현";

        String plainValues = "fortune?name?"+name+"?year?"+year;
        String encryptValues = aesUtil.encrypt(plainValues);
        String encodeValues = URLEncoder.encode(encryptValues, StandardCharsets.UTF_8.toString());

        String address = "http://172.30.1.66:8080/share?" + encodeValues;
        System.out.println(address);

        String qrcode = getQRCodeImage(address,200,200);

        model.addAttribute("img", qrcode);

        return "qrcode";
    }


    @GetMapping("/share")
    public String cryptTests(HttpServletRequest request,
                                            Model model) throws Exception{
        String decodeValues = URLDecoder.decode(request.getQueryString(),
                                            StandardCharsets.UTF_8.toString());
        return "redirect:/fortune?" + decodeValues;
    }

    @GetMapping("/fortune")
    public String fortuneFromRedirect(HttpServletRequest request, Model model) throws Exception {


        String decryptValues = aesUtil.decrypt(request.getQueryString());

        String name = decryptValues.split("\\?")[2];
        String year = decryptValues.split("\\?")[4];

        model.addAttribute("name", name);
        model.addAttribute("year", year);

        return "result";
    }

}
