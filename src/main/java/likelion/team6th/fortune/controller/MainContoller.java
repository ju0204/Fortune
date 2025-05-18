package likelion.team6th.fortune.controller;

import likelion.team6th.fortune.config.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainContoller {

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

}
