package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceImplTest {

    /* 생성자 주입 방식을 사용하면 좋은점!
    *  이렇게 순수한 자바 테스트 코드를 작성할 수 있다 (@SpringBootTest가 아닌)
    *  테스트 코드를 작성하면서 필요한 의존관계를 주입해 조립할 수 있다~
    *  final 키워드를 사용할 수 있기 때문에 불변성 보장하며 누락(NullPointException)되는 것을 막아준다
    *  final 키워드를 붙이면 초기화 되지 않은 것을 컴파일 단계에서 알 수 있음
    * */
    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
