package likelion.team6th.fortune.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableSpringDataWebSupport
public class WebConfig {
    // 아무 메서드 없어도 됨. 이거 하나로 Pageable 바인딩 가능해짐.
}