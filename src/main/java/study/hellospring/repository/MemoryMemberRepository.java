package study.hellospring.repository;

import study.hellospring.domain.Member;
import java.util.*;



public class MemoryMemberRepository implements MemberRepository {

    // key값이 Long  , value는 Member
    private static Map<Long, Member> store = new HashMap<>();
    // 뒤에 L을 적엄줌으로써 4바이트가 아닌 8바이트에 넣는다.
    private static long sequence = 0L; // key값을 생성해주는 용도

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // loop로 돌린다.
                .filter(member -> member.getName().equals(name)) // 파라미터로 넘어온 name하고 같은경우만 필터링
                .findAny(); // 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
