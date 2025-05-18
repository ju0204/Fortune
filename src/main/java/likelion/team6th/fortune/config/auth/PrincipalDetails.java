package likelion.team6th.fortune.config.auth;


import likelion.team6th.fortune.entity.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails {

    private final Admin admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); //로그인 여부만 확인하기에 권한은 필요가 없다.
    }

    @Override
    public String getPassword() {
        return admin.getAdminPw();
    }

    @Override
    public String getUsername() {
        return admin.getAdminId();
    }
}
