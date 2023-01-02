package study.hellospring.service;


import org.springframework.transaction.annotation.Transactional;
import study.hellospring.domain.Member;
import study.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;


@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    // 외부에서 주입 해준다 = Dependency Injection (의존성 주입)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {

        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);

        // Long 타입임으로 member.getId()를 통해 반환
        return member.getId();


    }

    private void validateDuplicateMember(Member member) {
        // Optional , null일 가능성이 있으면 Optional로 감싼다.
        Optional<Member> result = memberRepository.findByName(member.getName());
        // result안에 값이 있으면
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    // 위 코드랑 같은 코드
    //         memberRepository.findByName(member.getName())
    //            .ifPresent(m -> {
    //            throw new IllegalStateException("이미 존재하는 회원입니다.");
    //        } );

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


    /** 시간 찍는 로직
     *    try {
     *             finally{
     *                 long finish = System.currentTimeMillis();
     *                 long timeMs = finish - start;
     *                 System.out.println("join = " + timeMs + "ms");
     *
     *             }
     *         }
     */

}
