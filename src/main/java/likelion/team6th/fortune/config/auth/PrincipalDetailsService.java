package likelion.team6th.fortune.config.auth;


import likelion.team6th.fortune.entity.Admin;
import likelion.team6th.fortune.repository.adminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final adminRepository adminrepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {

        Admin admin = adminrepository.findByAdminId(adminId);

        if(admin == null) {
            throw new UsernameNotFoundException("Id 혹은 Pw를 잘못 입력하였습니다.");
        }


        return new PrincipalDetails(admin);
    }
}
