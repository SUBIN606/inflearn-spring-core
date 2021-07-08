package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/* @ComponentScan은 @Component 애노테이션이 붙은 클래스들을 모두 읽는다
*  @Controller, @Service, @Repository, @Configuration 을 모두 대상에 포함한다
*  왜냐하면 위의 애너테이션들은 모두 @Component를 포함하고 있기 때문에~
* */
@ComponentScan(
        // 탐색할 시작 위치 지정 -> 없으면 모든 자바 코드를 탐색하기 때문에 지정해주는 것이 좋다!
        // 지정하지 않으면? 기본 위치: 해당클래스 부터 시작. 즉, basePackage를 지정하지 않아도 현재 클래스가 위치한 hello.core가 기본 시작위치가 된다
        // 권장하는 방법: 패키지 위치를 지정하지 않고, 설정 정보 클래스를 루트 디렉터리에 두는 것 (스프링 부트 기본..main Application을 생각해보자..)
        basePackages = "hello.core",

        // 현재 AppConfig에서 수동으로 등록한 정보를 제외하기 위해 excludeFilters를 사용해 @Configuration 애노테이션이 붙은 클래스는 제외한다
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // @Configuration 애노테이션은 @Component를 포함하고 있음
)
@Configuration
public class AutoAppConfig {

}
