package study.hellospring.repository;

import study.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); // Member가 저장소에 저장된다.
    Optional<Member> findById(Long id); // 저장소에서 findById를 통해 id를 찾을수있다.
    Optional<Member> findByName(String name); // 저장소에서 findByName을 통해 Name을 찾을수있다.
    List<Member> findAll(); // 모든 Member 의 List를 반환
}
