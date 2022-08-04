package project1.doyouknow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project1.doyouknow.domain.board.Board;

@Repository
public interface BoardSpringJpaRepository extends JpaRepository<Board, Long> {
}
