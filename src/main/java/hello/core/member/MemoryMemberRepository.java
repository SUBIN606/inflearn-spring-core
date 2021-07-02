package hello.core.member;

import java.util.HashMap;
import java.util.Map;

/* 아직 DB 저장 방식이 정해지지 않았다는 가정하에.. */
public class MemoryMemberRepository implements MemberRepository{

    /* 실무에서는 동시성 이슈를 고려해 ConcurrentHashMap을 사용한다 */
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
