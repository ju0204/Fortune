package likelion.team6th.fortune.config.auth;

import likelion.team6th.fortune.entity.Admin;
import likelion.team6th.fortune.repository.AdminRepository;
import likelion.team6th.fortune.util.PropertiesGetter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BaseInitializer implements CommandLineRunner {

    private final PropertiesGetter properties;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        if(adminRepository.findByAdminId("admin") == null) {
            Admin admin = Admin.of("admin",
                    passwordEncoder.encode(properties.getAdminPw()));
            adminRepository.save(admin);
        }
    }
}
