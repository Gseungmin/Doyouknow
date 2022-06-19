package project1.doyouknow.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.service.MemberService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;

    @GetMapping("/myPage")
    public String myPageForm()
    {
        return "member/myPage";
    }

    @GetMapping("/myPage/login")
    public String myPageLoginForm(Model model, @RequestParam String loginId)
    {
        Optional<Member> member = memberService.findAnyByLoginID(loginId);
        model.addAttribute("member", member.get());
        return "member/myPage";
    }
}
