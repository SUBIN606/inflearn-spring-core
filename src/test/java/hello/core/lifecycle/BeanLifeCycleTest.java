package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    void lifeCycleTest() {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = applicationContext.getBean(NetworkClient.class);
        applicationContext.close();
    }

    @Configuration
    static class LifeCycleConfig {

        // 스프링 빈 등록
        // 스프링 빈의 라이프 사이클
        // 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸 전 콜백 -> 스프링 종료
        @Bean(initMethod = "init", destroyMethod = "close")     // 빈 등록 시 초기화, 소멸 메서드를 설정한다.
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();  // 객체 생성 후
            networkClient.setUrl("http://hello-spring.dev");    // url setting
            return networkClient;
        }

    }
}
