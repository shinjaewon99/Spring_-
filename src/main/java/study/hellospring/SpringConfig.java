package study.hellospring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.hellospring.aop.TimeTraceAop;
import study.hellospring.repository.JdbcMemberRepository;
import study.hellospring.repository.JdbcTemplateMemberRepository;
import study.hellospring.repository.JpaMemberRepository;
import study.hellospring.repository.MemberRepository;
import study.hellospring.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired // 생성자가 한개일경우 생략가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    */
    /*

    private DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
*/

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }

    /*
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();

//        return new JdbcMemberRepository(dataSource);

//        return new JdbcTemplateMemberRepository(dataSource);

        return new JpaMemberRepository(em);

    }
    */
}
