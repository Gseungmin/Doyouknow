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
import project1.doyouknow.repository.BoardSpringJpaRepository;
import project1.doyouknow.repository.MemberSpringJpaRepository;
import project1.doyouknow.repository.PostSpringJpaRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostSpringJpaRepository postRepository;
    private final MemberSpringJpaRepository memberRepository;
    private final BoardSpringJpaRepository boardRepository;
    private final FileStore fileStore;

    public void upload(Post post) {
        postRepository.save(post);
    }

    public Post findPost(Long id) {
        return postRepository.findById(id).get();
    }

    public List<Board> findBoardAll() {
        return boardRepository.findAll();
    }

    public Optional<Board> findBoard(String type) {
        List<Board> allBoard = boardRepository.findAll();
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
        //Member의 post수가 늘어난다.
        member.postCreate();
        /**MEMBER_POST 양방향 연관관계
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
        //Member의 post수가 늘어난다.
        member.postCreate();
        /**MEMBER_POST 양방향 연관관계
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
        //Member의 post수가 늘어난다.
        member.postCreate();
        /**MEMBER_POST 양방향 연관관계
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
        //post를 만들면 보드의 post수가 늘어난다.
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
        //Board에 있는 Post 수 줄여줌
        post.getBoard().delete();
        //Member의 Post수를 줄여주고 삭제된 Post수를 늘려준다.
        post.getMember().delete();
        post.setDelete(true);
    }

    /**Back Up Post**/
    public void backUp(Post post) {
        //Member의 Post수를 늘려주고 추가된 Post수를 늘려준다.
        post.getMember().create();
        //Board에 있는 Post 수 늘려줌
        post.getBoard().create();
        post.setDelete(false);
    }

}
