package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// 6. Lombok 으로 생성자 의존관계 주입
@RequiredArgsConstructor
@Component
public class OrderServiceImpl implements OrderService {

    // 4. 구현체 직접 선택하는 부분은 이제 지운다!
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 1. 정액 할인 정책
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 2. 정률 할인 정책으로 변경! -> 클라이언트 코드를 고쳐야 함.. -> 문제점 발견
    // 클라이언트(OrderServiceImpl)가 DiscountPolicy 뿐만 아니라 구현체까지 의존하고 있음 -> DIP, OCP 위반
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 3. DIP 위반하지 않도록 인터페이스(역할)에만 의존하도록 하자! -> NullPointException 발생
    // 이 문제를 해결하기 위헤서는 누군가가 클라이언트(OrderServiceImpl)에 DiscountPolicy 구현 객체를 대신 생성하고 주입해주어야 한다
    // 클라이언트(OrderServiceImpl)는 직접 구현체를 할당하는 역할까지는 하지 않아도 됨 -> 관심사를 분리해라
    //private DiscountPolicy discountPolicy;

    // 5. 생성자 의존관계 주입으로 변경
    private final MemberRepository memberRepository;
//    //private final DiscountPolicy fixDiscountPolicy; // 7. 조회 빈이 두 개 이상일 때 field 명을 특정 빈 이름으로 설정하면 매칭된다.
    private final DiscountPolicy discountPolicy;        // 9. @Primary가 붙은 우선순위가 우위에 있는 빈을 찾는다.

    // 6. Lombok의 RequiredArgsConstructor 으로 생성자 의존관계 주입했기 때문에 주석처리
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {    // 8. @Qualifier를 이용해 중복 빈 처리
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        /* 회원정보 조회 */
        Member member = memberRepository.findById(memberId);
        /* 회원 등급에 맞는 할인 조회 */
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 싱글톤 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
