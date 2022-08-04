package project1.doyouknow.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project1.doyouknow.SessionConst;
import project1.doyouknow.annotation.MemberCheck;
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

     @GetMapping("/edit/person/{postId}")
     public String editPersonForm(@PathVariable("postId") Long postId, @ModelAttribute("newPost") PersonEditForm newPost, Model model,
                                  @ModelAttribute("form") ImageForm imageForm, @MemberCheck Member member) {
     Person post = (Person) postService.findPost(postId);
     model.addAttribute("person", post);
     model.addAttribute("member", member);
     return "post/edit/editPeople";
     }

     @PostMapping("/edit/person/{postId}")
     public String editPerson(@PathVariable("postId") Long postId, @ModelAttribute("newPost") PersonEditForm newPost,
     Model model, @ModelAttribute("form") ImageForm imageForm, @MemberCheck Member member) throws IOException {
     Person post = (Person) postService.findPost(postId);
     model.addAttribute("person", post);
     model.addAttribute("member", member);
     MultipartFile imageFile = imageForm.getImageFile();
     postService.editPerson(post,newPost,imageFile);
     return "redirect:/";
     }

     @GetMapping("/edit/place/{postId}")
     public String editPlaceForm(@PathVariable("postId") Long postId, @ModelAttribute("newPost") PlaceEditForm newPost, Model model,
                                 @ModelAttribute("form") ImageForm imageForm, @MemberCheck Member member) {
     Place post = (Place) postService.findPost(postId);
     model.addAttribute("place", post);
     model.addAttribute("member", member);
     return "post/edit/editPlace";
     }

     @PostMapping("/edit/place/{postId}")
     public String editPlace(@PathVariable("postId") Long postId, @ModelAttribute("newPost") PlaceEditForm newPost,
                             @ModelAttribute("form") ImageForm imageForm, Model model, @MemberCheck Member member) throws IOException {
     Place post = (Place) postService.findPost(postId);
     model.addAttribute("place", post);
     model.addAttribute("member", member);
     MultipartFile imageFile = imageForm.getImageFile();
     postService.editPlace(post,newPost,imageFile);
     return "redirect:/";
     }

     @GetMapping("/edit/video/{postId}")
     public String editVideoForm(@PathVariable("postId") Long postId, @ModelAttribute("newPost") VideoEditForm newPost, Model model,
                                 @ModelAttribute("form") ImageForm imageForm, @MemberCheck Member member) {
     Video post = (Video) postService.findPost(postId);
     model.addAttribute("video", post);
     model.addAttribute("member", member);
     return "post/edit/editVideo";
     }

     @PostMapping("/edit/video/{postId}")
     public String editVideo(@PathVariable("postId") Long postId, @ModelAttribute("newPost") VideoEditForm newPost,
                             @ModelAttribute("form") ImageForm imageForm, Model model, @MemberCheck Member member) throws IOException {
     Video post = (Video) postService.findPost(postId);
     model.addAttribute("video", post);
     model.addAttribute("member", member);
     MultipartFile imageFile = imageForm.getImageFile();
     postService.editVideo(post,newPost,imageFile);
     return "redirect:/";
     }
}
