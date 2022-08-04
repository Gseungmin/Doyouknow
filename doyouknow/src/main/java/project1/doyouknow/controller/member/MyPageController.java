package project1.doyouknow.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project1.doyouknow.SessionConst;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.domain.post.saveForm.Post;
import project1.doyouknow.service.MemberService;
import project1.doyouknow.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("/myPage/{memberId}")
    public String myPageLoginForm(Model model, @PathVariable("memberId") Long memberId)
    {
        Optional<Member> member = memberService.findById(memberId);
        model.addAttribute("member", member.get());
        return "member/myPage";
    }


    @GetMapping("/myPage/backUp/{memberId}")
    public String backUpForm(@PathVariable("memberId") Long memberId, Model model) {
        Optional<Member> member = memberService.findById(memberId);
        model.addAttribute("member", member.get());
        return "post/backUp/backPage";
    }

    @PostMapping("/backUp/{postId}")
    public String backUpPost(@PathVariable("postId") Long postId) {
        Post post = postService.findPost(postId);
        Member member = post.getMember();
        postService.backUp(post);
        return "redirect:/myPage/backUp/"+member.getId();
    }

    @PostMapping("/delete/{postId}")
    public String delete(@PathVariable("postId") Long postId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Post post = postService.findPost(postId);
        postService.delete(post);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        return "redirect:/myPage/" + member.getId();
    }
}
