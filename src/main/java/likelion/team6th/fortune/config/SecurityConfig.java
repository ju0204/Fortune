package likelion.team6th.fortune.config;

import likelion.team6th.fortune.entity.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean   //securityFilterChain을 스프링 빈 컨테이너에 등록합니다.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors-> cors.disable());


        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                    .requestMatchers("/admin/**").authenticated() //admin 요청에 대해서 유저가 인증이 되었다면.
                    .anyRequest().permitAll()); //어떠한 요청이든 접근을 허용.

        http
                .formLogin(form ->
                        form
                                .loginPage("/login")    //로그인 페이지의 경로 [GET]
                                .loginProcessingUrl("/login") //로그인 요청 경로 [POST]
                                .failureUrl("/fail") //로그인 실패시 해당 페이지로 이동합니다.
                                .defaultSuccessUrl("/admin/list")); //로그인 성공시 해당 페이지로 이동합니다.

        http
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true) //세션을 삭제합니다.
                                .deleteCookies("JSESSIONID")); //쿠키에 저장중이던 토큰을 삭제.

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
