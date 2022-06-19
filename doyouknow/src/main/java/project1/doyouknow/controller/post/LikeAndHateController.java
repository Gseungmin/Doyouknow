package project1.doyouknow.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project1.doyouknow.SessionConst;
import project1.doyouknow.domain.board.Board;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.domain.post.saveForm.Post;
import project1.doyouknow.service.LikeService;
import project1.doyouknow.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Transactional
public class LikeAndHateController {

    private final LikeService likeService;
    private final PostService postService;

    @PostMapping("/like/{postId}")
    public String likes(@PathVariable("postId")Long postId, HttpServletRequest request) {
        Post post = postService.findPost(postId);
        Board board = post.getBoard();
        String boardName = board.getType();
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        likeService.clickLikes(member.getLoginId(),postId);
        return "redirect:/board/"+boardName;
    }

    @PostMapping("/hate/{postId}")
    public String hates(@PathVariable("postId")Long postId, HttpServletRequest request) {
        Post post = postService.findPost(postId);
        Board board = post.getBoard();
        String boardName = board.getType();
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        likeService.clickHates(member.getLoginId(),postId);
        return "redirect:/board/"+boardName;
    }
}
