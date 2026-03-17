package onetomany.game1Scoreboard;

import org.springframework.data.jpa.repository.JpaRepository;
public interface game1ScoreboardRepository extends JpaRepository<game1User, Long>{
    game1User findById(int id);

    void deleteByUsername(String username);
}
