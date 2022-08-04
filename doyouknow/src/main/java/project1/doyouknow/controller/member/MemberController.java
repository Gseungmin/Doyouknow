package project1.doyouknow.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project1.doyouknow.SessionConst;
import project1.doyouknow.domain.member.LoginForm;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm")LoginForm loginForm) {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult,
                        HttpServletRequest request, @RequestParam(defaultValue = "/")String requestURL) {
        if (bindingResult.hasErrors()) {
            return "member/login";
        }
        Optional<Member> findMember = memberService.login(loginForm.getLoginId(), loginForm.getPassword());
        if (findMember.isEmpty()) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "member/login";
        }
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,findMember.get());
        return "redirect:/";
    }

    @GetMapping("/join")
    public String addForm(@ModelAttribute("member")Member member) {
        return "member/addMember";
    }

    @PostMapping("/join")
    public String addMember(@Validated @ModelAttribute("member")Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/addMember";
        }
        Optional<Member> check1 = memberService.findAnyByLoginID(member.getLoginId());
        if (check1.isPresent()) {
            bindingResult.reject("addFail", "이미 존재하는 아이디 입니다.");
            return "member/addMember";
        }
        Optional<Member> check2 = memberService.findAnyByNickname(member.getNickname());
        if (check2.isPresent()) {
            bindingResult.reject("addFail2", "이미 존재하는 닉네임 입니다.");
            return "member/addMember";
        }
        memberService.join(member);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
