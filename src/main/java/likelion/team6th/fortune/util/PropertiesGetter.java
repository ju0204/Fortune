package likelion.team6th.fortune.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:admin.properties")
public class PropertiesGetter {

    @Value("${admin.account.password}")
    private String adminPw;

    public String getAdminPw() {
        return adminPw;
    }

}
