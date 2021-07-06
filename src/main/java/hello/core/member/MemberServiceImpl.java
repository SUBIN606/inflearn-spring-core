package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 1. 클라이언트(MemberServiceImpl)가 직접 구현체를 선택했었다
    //private final MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    // 2. 이제 AppConfig 에서 구현체 선택을 관리한다.
    // 추상화에만 의존할 수 있다 -> DIP
    private final MemberRepository memberRepository;

    // 3. 생성자를 통해 구현체가 선택된다 (생성자 의존관계 주입)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        /* 다형성에 의해 override 된 MemoryMemberRepository의 save 메서드가 호출된다.. */
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 싱글톤 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
