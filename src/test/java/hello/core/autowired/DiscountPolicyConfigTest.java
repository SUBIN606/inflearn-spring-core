package hello.core.autowired;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountPolicyConfigTest {

    @DisplayName("별도 설정 정보를 만들고 수동으로 등록한다")
    @Test
    void configTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DiscountPolicyConfig.class, DiscountService.class);
        DiscountService discountService = applicationContext.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");

        assertThat(discountPrice).isEqualTo(2000);
    }

    static class DiscountService {

        private final Map<String, DiscountPolicy> discountPolicyMap;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap) {
            this.discountPolicyMap = policyMap;
        }

        public int discount(Member member, int price, String policy) {
            DiscountPolicy discountPolicy = discountPolicyMap.get(policy);
            return discountPolicy.discount(member, price);
        }
    }

    @Configuration
    static class DiscountPolicyConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }

    }

}
