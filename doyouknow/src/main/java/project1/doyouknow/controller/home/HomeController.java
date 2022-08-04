package project1.doyouknow.controller.home;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project1.doyouknow.annotation.MemberCheck;
import project1.doyouknow.domain.board.SortedBoardForm;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.service.PostService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String home(@MemberCheck Member member, Model model)
    {
        List<SortedBoardForm> boards = postService.SortedBoardForHome();

        model.addAttribute("boards", boards);
        if (member == null) {
            return "home";
        }
        model.addAttribute("member", member);
        return "loginHome";
    }
}
