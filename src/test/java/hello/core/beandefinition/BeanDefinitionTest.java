package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    /* ApplicationContext로 부르면 getBeanDefinition을 호출하지 못함 */
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    /*
    *  1. 직접 스프링 빈 등록하는 방법 => xml
    *  2. 우회하여 등록하는 방법 - factoryMethod 이용 => 자바 설정(AppConfig)
    * */

    /* xml으로 bean 설정했을 때 */
   // GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext("appConfig.xml");

    @DisplayName("빈 설정 메타정보 확인")
    @Test
    void findApplicationBean() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = applicationContext.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName
                    + " beanDefinition = " + beanDefinition);
            }
        }
    }

}
