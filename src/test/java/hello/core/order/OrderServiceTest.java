package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void setUp() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @DisplayName("주문테스트")
    @Test
    void createOrder() {
        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "Curry", Grade.VIP);
        memberService.join(member);

        //when
        Order order = orderService.createOrder(memberId, "Shoes", 130000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(13000);
    }
}
