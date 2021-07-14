package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = applicationContext.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);

        // 정액 할인
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        // 정률 할인
        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");

        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        /* 동적으로 bean을 주입받는다.. */
        private final Map<String, DiscountPolicy> policyMap;    // map으로 모든 discountPolicy를 주입받는다
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
            /*
            * policyMap = {fixDiscountPolicy=hello.core.discount.FixDiscountPolicy@6b98a075, rateDiscountPolicy=hello.core.discount.RateDiscountPolicy@2e61d218}
            * policies = [hello.core.discount.FixDiscountPolicy@6b98a075, hello.core.discount.RateDiscountPolicy@2e61d218]
            * */
        }

        public int discount(Member member, int price, String discountCode) {

            // 넘어온 빈 이름(key)를 통해 실제 사용할 구현체를 정한다
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            return discountPolicy.discount(member, price);
        }
    }
}
