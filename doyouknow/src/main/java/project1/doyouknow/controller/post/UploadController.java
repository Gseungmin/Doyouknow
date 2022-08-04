package project1.doyouknow.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project1.doyouknow.domain.image.FileStore;
import project1.doyouknow.domain.image.Image;
import project1.doyouknow.domain.image.ImageForm;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.domain.post.saveForm.Person;
import project1.doyouknow.domain.post.saveForm.Place;
import project1.doyouknow.domain.post.saveForm.Video;
import project1.doyouknow.service.MemberService;
import project1.doyouknow.service.PostService;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UploadController {

    private final MemberService memberService;
    private final PostService postService;
    private final FileStore fileStore;

    @GetMapping("/upload/person/{memberId}")
    public String uploadPersonForm(@PathVariable("memberId") Long memberId, @ModelAttribute("person") Person person, Model model,
                                        @ModelAttribute("form") ImageForm imageForm, @ModelAttribute("image") Image image)
    {
        Member member = memberService.findById(memberId).get();
        model.addAttribute("member", member);
        return "post/upload/uploadPeople";
    }

    @PostMapping("/upload/person/{memberId}")
    public String uploadPerson(@PathVariable("memberId") Long memberId,
                               @Validated @ModelAttribute("person") Person person, BindingResult bindingResult,
                               Model model, @ModelAttribute("form") ImageForm imageForm,
                               @ModelAttribute("image") Image image) throws IOException {
        if (bindingResult.hasErrors()) {
            return "post/upload/uploadPeople";}
        Member member = memberService.findById(memberId).get();
        model.addAttribute("member", member);
        MultipartFile multipartFile = imageForm.getImageFile();
        if (multipartFile.isEmpty()) {
            bindingResult.reject("AttachFail","첨부파일을 등록해주세요.");
            return "post/upload/uploadPeople";
        }
        fileStore.storeFile(multipartFile, image);
        postService.makePerson(person, member.getLoginId(), image);
        postService.upload(person);
        return "redirect:/";}

    @GetMapping("/upload/place/{memberId}")
    public String uploadPlaceForm(@PathVariable("memberId") Long memberId, @ModelAttribute("place") Place place, Model model,
                                       @ModelAttribute("form") ImageForm imageForm, @ModelAttribute("image") Image image)
    {
        Member member = memberService.findById(memberId).get();
        model.addAttribute("member", member);
        return "post/upload/uploadPlace";
    }

    @PostMapping("/upload/place/{memberId}")
    public String uploadPlace(@PathVariable("memberId") Long memberId,
                              @Validated @ModelAttribute("place") Place place, BindingResult bindingResult,
                              Model model,
                              @ModelAttribute("form") ImageForm imageForm, @ModelAttribute("image") Image image) throws IOException {
        if (bindingResult.hasErrors()) {
            return "post/upload/uploadPlace";}
        Member member = memberService.findById(memberId).get();
        model.addAttribute("member", member);
        MultipartFile multipartFile = imageForm.getImageFile();
        if (multipartFile.isEmpty()) {
            bindingResult.reject("AttachFail","첨부파일을 등록해주세요.");
            return "post/upload/uploadPlace";
        }
        fileStore.storeFile(multipartFile, image);
        postService.makePlace(place, member.getLoginId(), image);
        postService.upload(place);
        return "redirect:/";}

    @GetMapping("/upload/video/{memberId}")
    public String uploadVideoForm(@PathVariable("memberId") Long memberId, @ModelAttribute("video") Video video, Model model,
                                       @ModelAttribute("form") ImageForm imageForm, @ModelAttribute("image") Image image)
    {
        Member member = memberService.findById(memberId).get();
        model.addAttribute("member", member);
        return "post/upload/uploadVideo";
    }

    @PostMapping("/upload/video/{memberId}")
    public String uploadVideo(@PathVariable("memberId") Long memberId,
                              @Validated @ModelAttribute("video") Video video, BindingResult bindingResult,
                              Model model,
                              @ModelAttribute("form") ImageForm imageForm, @ModelAttribute("image") Image image) throws IOException {
        if (bindingResult.hasErrors()) {
            return "post/upload/uploadVideo";}
        Member member = memberService.findById(memberId).get();
        model.addAttribute("member", member);
        MultipartFile multipartFile = imageForm.getImageFile();
        if (multipartFile.isEmpty()) {
            bindingResult.reject("AttachFail","첨부파일을 등록해주세요.");
            return "post/upload/uploadVideo";
        }
        fileStore.storeFile(multipartFile, image);
        postService.makeVideo(video, member.getLoginId(), image);
        postService.upload(video);
        return "redirect:/";}
}
