package project1.doyouknow.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project1.doyouknow.api.dto.boardDto.BoardPostDto;
import project1.doyouknow.api.dto.errorDto.ErrorDto;
import project1.doyouknow.api.dto.errorDto.ErrorInfo;
import project1.doyouknow.api.dto.memberDto.MemberPostCommentDto;
import project1.doyouknow.api.dto.memberDto.MemberPostLikeDto;
import project1.doyouknow.api.dto.memberDto.VipPostDtos;
import project1.doyouknow.api.dto.postDto.PostAndCommentDto;
import project1.doyouknow.api.dto.memberDto.MemberMembershipDto;
import project1.doyouknow.api.dto.postDto.PostContentDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ApiController {

    private final QueryRepository queryRepository;

    /**회원들의 멤버십 정보 조회**/
    @GetMapping("/member/membership")
    public List<MemberMembershipDto> membershipDto() {
        return queryRepository.findMemberAll().stream().map(member -> new MemberMembershipDto(member)).collect(Collectors.toList());
    }

    /**게시글과 게시글의 댓글 정보 조회**/
    @GetMapping("/posts/comments")
    public List<PostAndCommentDto> postAndCommentDtos() {
        return queryRepository.findPostAll().stream().map(post -> new PostAndCommentDto(post)).collect(Collectors.toList());
    }

    /**회원과 회원이 쓴 게시글 및 댓글 조회**/
    @GetMapping("/members/posts/comments")
    public List<MemberPostCommentDto> memberPostCommentDtos() {
        return queryRepository.findMemberAll().stream().map(member -> new MemberPostCommentDto(member)).collect(Collectors.toList());
    }

    /**회원이 좋아요를 누른 게시글 조회**/
    @GetMapping("/members/posts/likes")
    public List<MemberPostLikeDto> memberPostLikeDtos() {
        return queryRepository.findMemberAll().stream().map(member -> new MemberPostLikeDto(member)).collect(Collectors.toList());
    }

    /**게시판의 게시물들 조회**/
    @GetMapping("/boards/posts")
    public List<BoardPostDto> boardPostDtos() {
        return queryRepository.findBoardAll().stream().map(board -> new BoardPostDto(board)).collect(Collectors.toList());
    }

    /**Vip가 올린 게시물 조회**/
    @GetMapping("/member/vip/posts")
    public List<VipPostDtos> vipPostDtos() {
        return queryRepository.findMemberVipAll().stream().map(member -> new VipPostDtos(member)).collect(Collectors.toList());
    }

    /**좋아요가 count개 이상인 게시물 조회**/
    @GetMapping("/posts/likes")
    public List<PostContentDto> postContentLikesMoreDtos(@RequestParam(value = "count", defaultValue = "0") int count) {
        return queryRepository.findPostLikesMoreThan(count).stream().map(post -> new PostContentDto(post)).collect(Collectors.toList());
    }

    /**예외 처리**/
    @GetMapping("/api/error/{error}")
    public ErrorDto errorDto(@PathVariable("error") String error) {
        if (error.equals("member")) {
            throw new RuntimeException("회원 오류 발생");
        }
        if (error.equals("post")) {
            throw new IllegalArgumentException("게시글 오류 발생");
        }
        return new ErrorDto(error);
    }

    /**Illegal 예외 처리를 위한 핸들러**/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorInfo illegalExHandle(IllegalArgumentException e) {
        return new ErrorInfo("IllegalEx", e.getMessage());
    }

    /**Runtime 예외 처리를 위한 핸들러**/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErrorInfo RuntimeExHandle(RuntimeException e) {
        return new ErrorInfo("RuntimeEx", e.getMessage());
    }
}
