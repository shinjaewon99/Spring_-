package study.hellospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.hellospring.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member , Long> , MemberRepository{



    // JPQL  Select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
