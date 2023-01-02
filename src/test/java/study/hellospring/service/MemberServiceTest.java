package study.hellospring.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.hellospring.domain.Member;

import org.junit.jupiter.api.Assertions.*;
import study.hellospring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원_가입() {

        // given 무언가가 주어졌을때
        Member member = new Member();
        member.setName("신재원");


        // when 그거를 실행했을때
        Long saveId = memberService.join(member);

        // then 결과가 이게 나와야된다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    void 중복_회원_가입_예외() {
        //given
        Member member = new Member();
        member.setName("신재원");

        Member member1 = new Member();
        member1.setName("신재원");
        //when
        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member1));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");



/*
        try{
            memberService.join(member1);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}