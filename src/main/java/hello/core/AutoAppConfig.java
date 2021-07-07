package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/* @ComponentScan은 @Component 애노테이션이 붙은 클래스들을 모두 읽는다 */
@ComponentScan(
        // 현재 AppConfig에서 수동으로 등록한 정보를 제외하기 위해 excludeFilters를 사용해 @Configuration 애노테이션이 붙은 클래스는 제외한다
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // @Configuration 애노테이션은 @Component를 포함하고 있음
)
@Configuration
public class AutoAppConfig {

}
