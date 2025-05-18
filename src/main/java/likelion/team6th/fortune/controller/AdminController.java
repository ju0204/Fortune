package likelion.team6th.fortune.controller;


import likelion.team6th.fortune.entity.Admin;
import likelion.team6th.fortune.repository.adminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class AdminController {

    private final adminRepository adminrepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/create-admin")
    public String createAdmin() {

        Admin admin = Admin.of("admin", passwordEncoder.encode("admin"));

        try {
            adminrepository.save(admin);
        } catch (Exception e) {
            return "redirect:/";
        }

        return "redirect:/";
    }

}
