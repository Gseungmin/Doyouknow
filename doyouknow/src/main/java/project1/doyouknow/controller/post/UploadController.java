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
import project1.doyouknow.domain.post.saveForm.Person;
import project1.doyouknow.domain.post.saveForm.Place;
import project1.doyouknow.domain.post.saveForm.Video;
import project1.doyouknow.service.PostService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class UploadController {

    private final PostService postService;
    private final FileStore fileStore;

    @GetMapping("/upload/person")
    public String uploadPersonForm(@ModelAttribute("person") Person person, Model model, ImageForm imageForm)
    {
        model.addAttribute("form", imageForm);
        return "post/upload/uploadPeople";
    }

    @GetMapping("/upload/person/login")
    public String uploadPersonLoginForm(@RequestParam String loginId, @ModelAttribute("person") Person person, Model model,
                                        @ModelAttribute("form") ImageForm imageForm, @ModelAttribute("image") Image image)
    {
        model.addAttribute("loginId", loginId);
        return "post/upload/uploadPeople";
    }

    @PostMapping("/upload/person/login")
    public String uploadPerson(@RequestParam String loginId,
                               @Validated @ModelAttribute("person") Person person, BindingResult bindingResult,
                               Model model, @ModelAttribute("form") ImageForm imageForm,
                               @ModelAttribute("image") Image image) throws IOException {
        if (bindingResult.hasErrors()) {
            return "post/upload/uploadPeople";}
        model.addAttribute("loginId", loginId);
        MultipartFile multipartFile = imageForm.getImageFile();
        if (multipartFile.isEmpty()) {
            bindingResult.reject("AttachFail","첨부파일을 등록해주세요.");
            return "post/upload/uploadPeople";
        }
        fileStore.storeFile(multipartFile, image);
        postService.makePerson(person, loginId, image);
        postService.upload(person);
        return "redirect:/";}

    @GetMapping("/upload/place")
    public String uploadPlaceForm(@ModelAttribute("place") Place place)
    {
        return "post/upload/uploadPlace";
    }

    @GetMapping("/upload/place/{loginId}")
    public String uploadPlaceLoginForm(@RequestParam("loginId") String loginId, @ModelAttribute("place") Place place, Model model,
                                       @ModelAttribute("form") ImageForm imageForm, @ModelAttribute("image") Image image)
    {
        model.addAttribute("loginId", loginId);
        return "post/upload/uploadPlace";
    }

    @PostMapping("/upload/place/{loginId}")
    public String uploadPlace(@RequestParam("loginId") String loginId,
                              @Validated @ModelAttribute("place") Place place, BindingResult bindingResult,
                              Model model,
                              @ModelAttribute("form") ImageForm imageForm, @ModelAttribute("image") Image image) throws IOException {
        if (bindingResult.hasErrors()) {
            return "post/upload/uploadPlace";}
        model.addAttribute("loginId", loginId);
        MultipartFile multipartFile = imageForm.getImageFile();
        if (multipartFile.isEmpty()) {
            bindingResult.reject("AttachFail","첨부파일을 등록해주세요.");
            return "post/upload/uploadPlace";
        }
        fileStore.storeFile(multipartFile, image);
        postService.makePlace(place, loginId, image);
        postService.upload(place);
        return "redirect:/";}

    @GetMapping("/upload/video")
    public String uploadVideoForm(@ModelAttribute("video") Video video)
    {
        return "post/upload/uploadVideo";
    }

    @GetMapping("/upload/video/{loginId}")
    public String uploadVideoLoginForm(@RequestParam("loginId") String loginId, @ModelAttribute("video") Video video, Model model,
                                       @ModelAttribute("form") ImageForm imageForm, @ModelAttribute("image") Image image)
    {
        model.addAttribute("loginId", loginId);
        return "post/upload/uploadVideo";
    }

    @PostMapping("/upload/video/{loginId}")
    public String uploadVideo(@RequestParam("loginId") String loginId,
                              @Validated @ModelAttribute("video") Video video, BindingResult bindingResult,
                              Model model,
                              @ModelAttribute("form") ImageForm imageForm, @ModelAttribute("image") Image image) throws IOException {
        if (bindingResult.hasErrors()) {
            return "post/upload/uploadVideo";}
        model.addAttribute("loginId", loginId);
        MultipartFile multipartFile = imageForm.getImageFile();
        if (multipartFile.isEmpty()) {
            bindingResult.reject("AttachFail","첨부파일을 등록해주세요.");
            return "post/upload/uploadVideo";
        }
        fileStore.storeFile(multipartFile, image);
        postService.makeVideo(video, loginId, image);
        postService.upload(video);
        return "redirect:/";}
}
