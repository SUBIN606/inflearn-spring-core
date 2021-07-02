package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 애플리케이션 전체 환경을 설정하고 구성한다
*  애플리케이션 실제 동작에 필요한 구현 객체를 생성해줌
*  생성자를 통해 의존관계 주입
* */
/* 역할과 구현을 메서드 명만 봐도 명확하게 파악할 수 있음 */
/* 사용 영역과 구성 영역으로 분리 */
@Configuration
public class AppConfig {

    /* return 값만 변경하면 바로 구현을 변경/확장 할 수 있다! */
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /* 할인 정책이 정액 -> 정률로 변경되었다면, return 값만 변경해주면 된다! */
    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
