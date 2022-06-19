package project1.doyouknow.init;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project1.doyouknow.domain.board.Board;
import project1.doyouknow.domain.comment.Comment;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.domain.member.Membership;
import project1.doyouknow.domain.post.saveForm.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.init();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void init() {
            List<Board> board = em.createQuery("select b from Board b", Board.class).getResultList();
            if (board.isEmpty()) {
                //Board 생성
                Board Board1 = getBoard("People", 0, "한국 유명 인사에 대해 알고싶다면 아래 버튼을 눌러주세요");
                em.persist(Board1);

                Board Board2 = getBoard("Place", 1, "한국 유명 장소에 대해 알고싶다면 아래 버튼을 눌러주세요");
                em.persist(Board2);

                Board Board3 = getBoard("Video", 2, "한국 유명 영화 및 드라마에 대해 알고싶다면 아래 버튼을 눌러주세요");
                em.persist(Board3);

                //Member 생성
                Member member1 = getMember("admin", "admin", "admin", "admin");
                em.persist(member1);

                Member member2 = getMember("user1", "user1", "user1", "user1");
                em.persist(member2);
            }
        }

        private Member getMember(String name, String password, String nickname, String loginId) {
            Member member = new Member();
            member.setDeletePostCount(0);
            member.setPostCount(0);
            member.setName(name);
            member.setPassword(password);
            member.setNickname(nickname);
            member.setLoginId(loginId);
            member.setMembership(Membership.BASIC);
            return member;
        }

        private Board getBoard(String type, int Num, String message) {
            Board board = new Board();
            board.setPostCount(0);
            board.setType(type);
            board.setBoardNum(Num);
            board.setMessage(message);
            return board;
        }
    }
}
