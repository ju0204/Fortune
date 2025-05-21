package likelion.team6th.fortune.controller;

import likelion.team6th.fortune.config.auth.PrincipalDetails;
import likelion.team6th.fortune.util.AESUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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

        String qrcode = getQRCodeImage(address,200,200);

        model.addAttribute("img", qrcode);

        return "qrcode";
    }




}
