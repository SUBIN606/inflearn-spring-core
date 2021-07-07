package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

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
