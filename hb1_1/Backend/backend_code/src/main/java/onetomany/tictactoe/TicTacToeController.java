package onetomany.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tictactoe")
public class TicTacToeController {
    private final TicTacToeService ticTacToeService;

    @Autowired
    public TicTacToeController(TicTacToeService ticTacToeService) {
        this.ticTacToeService = ticTacToeService;
    }

    @PostMapping("/results")
    public ResponseEntity<Void> saveGameResult(@RequestParam String username, @RequestParam String result) {
        ticTacToeService.saveGameResult(username, result);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<Map<String, Integer>> getLeaderboard() {
        Map<String, Integer> leaderboard = ticTacToeService.getLeaderboard();
        return ResponseEntity.ok(leaderboard);
    }
}