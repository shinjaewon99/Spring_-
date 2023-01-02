package study.hellospring.repository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import study.hellospring.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 한개의 테스트 메소드가 끌날때마다 실행되면서 객체가 저장된거를 클리어해준다.
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save() {
        Member member = new Member();
        member.setName("신재원");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName(){
        Member member = new Member();
        member.setName("신재원 스프링");
        repository.save(member);

        Member member1 = new Member();
        member1.setName("신재원 스프링 1");
        repository.save(member1);

        Member result = repository.findByName("신재원 스프링").get();

        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findAll(){
        Member member = new Member();
        member.setName("신재원 스프링");
        repository.save(member);

        Member member1 = new Member();
        member1.setName("신재원 스프링 1");
        repository.save(member1);

        List<Member> findAllMembers = repository.findAll();

        assertThat(findAllMembers.size()).isEqualTo(2);
    }





}
