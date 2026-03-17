package onetomany.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TicTacToeService {
    private final GameResultRepository gameResultRepository;

    @Autowired
    public TicTacToeService(GameResultRepository gameResultRepository) {
        this.gameResultRepository = gameResultRepository;
    }

    public void saveGameResult(String username, String result) {
        GameResult gameResult = new GameResult(username, result);
        gameResultRepository.save(gameResult);
    }

    public List<GameResult> getAllGameResults() {
        return gameResultRepository.findAll();
    }

    public Map<String, Integer> getLeaderboard() {
        List<GameResult> allResults = getAllGameResults();
        Map<String, Integer> leaderboard = new HashMap<>();

        for (GameResult result : allResults) {
            String username = result.getUsername();
            int score = leaderboard.getOrDefault(username, 0);
            if (result.getResult().equalsIgnoreCase("win")) {
                score++;
            }
            leaderboard.put(username, score);
        }

        return leaderboard.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}