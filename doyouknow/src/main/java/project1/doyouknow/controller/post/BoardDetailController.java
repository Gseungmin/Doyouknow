package project1.doyouknow.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project1.doyouknow.SessionConst;
import project1.doyouknow.domain.board.Board;
import project1.doyouknow.domain.comment.Comment;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.domain.post.saveForm.Post;
import project1.doyouknow.service.CommentService;
import project1.doyouknow.service.MemberService;
import project1.doyouknow.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BoardDetailController {

    private final PostService postService;
    private final MemberService memberService;
    private final CommentService commentService;

    @GetMapping("/board/{boardName}")
    public String boardForm(@PathVariable("boardName") String boardName, Model model)
    {
        Optional<Board> findBoard = postService.findBoard(boardName);
        Board board = findBoard.get();
        postService.SortedBoard(board);
        model.addAttribute("board", board);
        return "post/contentPage";
    }

    @GetMapping("/board/{boardName}/login")
    public String boardLoginForm(@PathVariable("boardName") String boardName, Model model,
                                 @RequestParam String loginId)
    {
        Optional<Member> member = memberService.findAnyByLoginID(loginId);
        Optional<Board> findBoard = postService.findBoard(boardName);
        Board board = findBoard.get();
        postService.SortedBoard(board);
        model.addAttribute("member", member.get());
        model.addAttribute("board", board);
        return "post/contentPageLogin";
    }

    @GetMapping("/detail/{postId}")
    public String detailForm(@PathVariable("postId") Long postId, @ModelAttribute("newComment") Comment newComment,
                             Model model, HttpServletRequest request) {
        Post post = postService.findPost(postId);
        model.addAttribute("post", post);
        HttpSession session = request.getSession(false);
        if (session==null||session.getAttribute(SessionConst.LOGIN_MEMBER).equals(null)) {
            return "post/detail/postDetail";
        };
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("member", member);
        return "post/detail/postDetailLogin";
    }

    @PostMapping("/detail/{postId}")
    public String addComment(@PathVariable("postId") Long postId, @Validated @ModelAttribute("newComment") Comment newComment,
                             BindingResult bindingResult, Model model, HttpServletRequest request) {
        Post post = postService.findPost(postId);
        model.addAttribute("post", post);
        HttpSession session = request.getSession(false);
        Member member1 = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Optional<Member> findMember = memberService.findAnyByLoginID(member1.getLoginId());
        Member member = findMember.get();
        model.addAttribute("member", member);
        if (bindingResult.hasErrors()) {
            return "post/detail/postDetailLogin";
        }
        commentService.makeComment(newComment, post, member);
        return "redirect:/detail/{postId}";
    }
}
