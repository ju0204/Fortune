package likelion.team6th.fortune.util;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;



@Getter
@Component
@PropertySource("classpath:admin.properties")
public class PropertiesGetter {

    @Value("${admin.account.password}")
    private String adminPw;

    @Value("${data.crypt.key}")
    private String cryptkey;

    @Value("${app.server.address}")
    private String serverAddress;

}
