package study.hellospring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import study.hellospring.domain.Member;
import study.hellospring.service.MemberService;

import java.util.List;

@Controller
public class MemberController {

    private  MemberService memberService;

    @Autowired
    // 생성자를 주입을 권장
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

/* set방식 주입 (public으로 되어있어 외부에서 변환가능하다는 단점)

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
*/

/*
     필드 주입
     @Autowired private MemberService memberService;
*/

    // Url에 맞춰 조회만 해준다.
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    // Url에 맞춰 Form 방식에 맞춰 전달한다.
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> membersList = memberService.findMembers();
        model.addAttribute("members" , membersList);

        return "members/membersList";
    }
}
