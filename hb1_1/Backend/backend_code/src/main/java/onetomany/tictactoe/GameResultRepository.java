package onetomany.tictactoe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import onetomany.Users.User;
import java.util.List;

@Repository
public interface GameResultRepository extends JpaRepository<GameResult, Long> {

}