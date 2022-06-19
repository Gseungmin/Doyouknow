package project1.doyouknow.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project1.doyouknow.SessionConst;
import project1.doyouknow.domain.image.ImageForm;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.domain.post.saveForm.Person;
import project1.doyouknow.domain.post.saveForm.Place;
import project1.doyouknow.domain.post.saveForm.Post;
import project1.doyouknow.domain.post.saveForm.Video;
import project1.doyouknow.domain.post.editForm.PersonEditForm;
import project1.doyouknow.domain.post.editForm.PlaceEditForm;
import project1.doyouknow.domain.post.editForm.VideoEditForm;
import project1.doyouknow.service.MemberService;
import project1.doyouknow.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class EditController {

    private final PostService postService;
    private final MemberService memberService;

     @GetMapping("/edit/person/{postId}")
     public String editPersonForm(@PathVariable("postId") Long postId, @ModelAttribute("newPost") PersonEditForm newPost, Model model,
                                  @ModelAttribute("form") ImageForm imageForm) {
     Person post = (Person) postService.findPost(postId);
     model.addAttribute("person", post);
     return "post/edit/editPeople";
     }

     @PostMapping("/edit/person/{postId}")
     public String editPerson(@PathVariable("postId") Long postId, @ModelAttribute("newPost") PersonEditForm newPost,
     Model model, @ModelAttribute("form") ImageForm imageForm) throws IOException {
     Person post = (Person) postService.findPost(postId);
     model.addAttribute("person", post);
     MultipartFile imageFile = imageForm.getImageFile();
     postService.editPerson(post,newPost,imageFile);
     return "redirect:/";
     }


     @GetMapping("/edit/place/{postId}")
     public String editPlaceForm(@PathVariable("postId") Long postId, @ModelAttribute("newPost") PlaceEditForm newPost, Model model,
                                 @ModelAttribute("form") ImageForm imageForm) {
     Place post = (Place) postService.findPost(postId);
     model.addAttribute("place", post);
     return "post/edit/editPlace";
     }

     @PostMapping("/edit/place/{postId}")
     public String editPlace(@PathVariable("postId") Long postId, @ModelAttribute("newPost") PlaceEditForm newPost,
                             @ModelAttribute("form") ImageForm imageForm, Model model) throws IOException {
     Place post = (Place) postService.findPost(postId);
     model.addAttribute("place", post);
     MultipartFile imageFile = imageForm.getImageFile();
     postService.editPlace(post,newPost,imageFile);
     return "redirect:/";
     }

     @GetMapping("/edit/video/{postId}")
     public String editVideoForm(@PathVariable("postId") Long postId, @ModelAttribute("newPost") VideoEditForm newPost, Model model,
                                 @ModelAttribute("form") ImageForm imageForm) {
     Video post = (Video) postService.findPost(postId);
     model.addAttribute("video", post);
     return "post/edit/editVideo";
     }

     @PostMapping("/edit/video/{postId}")
     public String editVideo(@PathVariable("postId") Long postId, @ModelAttribute("newPost") VideoEditForm newPost,
                             @ModelAttribute("form") ImageForm imageForm, Model model) throws IOException {
     Video post = (Video) postService.findPost(postId);
     model.addAttribute("video", post);
     MultipartFile imageFile = imageForm.getImageFile();
     postService.editVideo(post,newPost,imageFile);
     return "redirect:/";
     }

    @GetMapping("/myPage/backUp/{memberId}")
    public String backUpForm(@PathVariable("memberId") String memberId, Model model) {
        Optional<Member> member = memberService.findAnyByLoginID(memberId);
        model.addAttribute("member", member.get());
        return "post/backUp/backPage";
    }

    @PostMapping("/delete/{postId}")
    public String delete(@PathVariable("postId") Long postId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Post post = postService.findPost(postId);
        postService.delete(post);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        return "redirect:/myPage/login?loginId="+member.getLoginId();
    }

    @PostMapping("/backUp/{postId}")
    public String backUpPost(@PathVariable("postId") Long postId) {
        Post post = postService.findPost(postId);
        Member member = post.getMember();
        postService.backUp(post);
        return "redirect:/myPage/backUp/"+member.getLoginId();
    }
}
