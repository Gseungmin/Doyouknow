package project1.doyouknow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project1.doyouknow.domain.board.Board;
import project1.doyouknow.domain.board.SortedBoardForm;
import project1.doyouknow.domain.image.FileStore;
import project1.doyouknow.domain.image.Image;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.domain.post.editForm.PersonEditForm;
import project1.doyouknow.domain.post.editForm.PlaceEditForm;
import project1.doyouknow.domain.post.editForm.VideoEditForm;
import project1.doyouknow.domain.post.saveForm.Person;
import project1.doyouknow.domain.post.saveForm.Place;
import project1.doyouknow.domain.post.saveForm.Post;
import project1.doyouknow.domain.post.saveForm.Video;
import project1.doyouknow.repository.MemberRepository;
import project1.doyouknow.repository.PostRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final FileStore fileStore;

    public void upload(Post post) {
        postRepository.save(post);
    }

    public Post findPost(Long id) {
        return postRepository.findById(id);
    }

    public List<Board> findBoardAll() {
        return postRepository.findAllBoard();
    }

    public Optional<Board> findBoard(String type) {
        List<Board> allBoard = postRepository.findAllBoard();
        Optional<Board> B = allBoard.stream().filter(board -> board.getType().equals(type)).findFirst();
        return B;
    }

    /**Sort Board**/
    public List<SortedBoardForm> SortedBoardForHome() {
        List<Board> boards = findBoardAll();
        List<SortedBoardForm> sortedBoards = boards.stream().map(board -> new SortedBoardForm(board)).collect(Collectors.toList());
        sortedBoards.stream().forEach(sortedBoardForm -> sortedBoardForm.getPosts().sort(((p1, p2) -> p2.getLikes()-p1.getLikes())));
        return sortedBoards;
    }

    public void SortedBoard(Board board) {
        board.getPosts().sort(((p1, p2) -> p2.getLikes()-p1.getLikes()));
    }

    /**Make Post**/
    public Person makePerson(Person person, String loginId, Image image) {
        Optional<Member> OpMember = memberRepository.findByLoginId(loginId);
        Member member = OpMember.get();
        //Member??? post?????? ????????????.
        member.postCreate();
        /**MEMBER_POST ????????? ????????????
        person.setMember(member);
        member.getPosts().add(person);**/
        person.addMember(member);

        person.addImage(image);
        person.setLikes(0);
        person.setHates(0);
        person.setView(0);
        person.setDelete(false);
        Optional<Board> peopleBoard = findBoard("People");
        Board board = peopleBoard.get();
        board.create();
        person.addBoard(board);
        return person;
    }

    public Place makePlace(Place place, String loginId, Image image) {
        Optional<Member> OpMember = memberRepository.findByLoginId(loginId);
        Member member = OpMember.get();
        //Member??? post?????? ????????????.
        member.postCreate();
        /**MEMBER_POST ????????? ????????????
        place.setMember(member);
        member.getPosts().add(place);**/
        place.addMember(member);

        place.addImage(image);
        place.setLikes(0);
        place.setHates(0);
        place.setView(0);
        place.setDelete(false);
        Optional<Board> placeBoard = findBoard("Place");
        Board board = placeBoard.get();
        board.create();
        place.addBoard(board);
        return place;
    }

    public Video makeVideo(Video video, String loginId, Image image) {
        Optional<Member> OpMember = memberRepository.findByLoginId(loginId);
        Member member = OpMember.get();
        //Member??? post?????? ????????????.
        member.postCreate();
        /**MEMBER_POST ????????? ????????????
        video.setMember(member);
         member.getPosts().add(video);
         **/
        video.addMember(member);

        //video.setImage(image);
        video.addImage(image);
        video.setLikes(0);
        video.setHates(0);
        video.setView(0);
        video.setDelete(false);
        Optional<Board> videoBoard = findBoard("Video");
        Board board = videoBoard.get();
        //post??? ????????? ????????? post?????? ????????????.
        board.create();
        video.addBoard(board);
        return video;
    }


    /**Edit Post**/
    public void editPerson(Person post, PersonEditForm newPost, MultipartFile imageFile) throws IOException {
        if (!newPost.getContent().isEmpty()) {
            post.setContent(newPost.getContent());
        }
        if (!newPost.getName().isEmpty()) {
            post.setName(newPost.getName());
        }
        if (!newPost.getBirth().isEmpty()) {
            post.setBirth(newPost.getBirth());
        }
        if (!imageFile.isEmpty()) {
            Image image = post.getImage();
            fileStore.storeFile(imageFile, image);
        }
    }

    public void editPlace(Place post, PlaceEditForm newPost, MultipartFile imageFile) throws IOException {
        if (!newPost.getContent().isEmpty()) {
            post.setContent(newPost.getContent());
        }
        if (!newPost.getName().isEmpty()) {
            post.setName(newPost.getName());
        }
        if (!newPost.getAddress().isEmpty()) {
            post.setAddress(newPost.getAddress());
        }
        if (!imageFile.isEmpty()) {
            Image image = post.getImage();
            fileStore.storeFile(imageFile, image);
        }
    }

    public void editVideo(Video post, VideoEditForm newPost, MultipartFile imageFile) throws IOException {
        if (!newPost.getContent().isEmpty()) {
            post.setContent(newPost.getContent());
        }
        if (!newPost.getName().isEmpty()) {
            post.setName(newPost.getName());
        }
        if (!newPost.getDirector().isEmpty()) {
            post.setDirector(newPost.getDirector());
        }
        if (!imageFile.isEmpty()) {
            Image image = post.getImage();
            fileStore.storeFile(imageFile, image);
        }
    }

    /**Delete Post**/
    public void delete(Post post) {
        //Board??? ?????? Post ??? ?????????
        post.getBoard().delete();
        //Member??? Post?????? ???????????? ????????? Post?????? ????????????.
        post.getMember().delete();
        post.setDelete(true);
    }

    /**Back Up Post**/
    public void backUp(Post post) {
        //Member??? Post?????? ???????????? ????????? Post?????? ????????????.
        post.getMember().create();
        //Board??? ?????? Post ??? ?????????
        post.getBoard().create();
        post.setDelete(false);
    }

}
