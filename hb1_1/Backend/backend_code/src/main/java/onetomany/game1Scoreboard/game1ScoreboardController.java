package onetomany.game1Scoreboard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class game1ScoreboardController {
    @Autowired
    game1ScoreboardRepository game1ScoreboardRepositoryy;
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    //    @GetMapping(path = "/reports")
//    List<Reports> getAllReports(){
//        return reportsRepository.findAll();
//    }
    @GetMapping(path = "/game1Score/")
    List<game1User> getScores(){
        List<game1User> list= game1ScoreboardRepositoryy.findAll();
        mergeService.mergeSort(list,0, list.size()-1);
        return list;
    }


    @PostMapping(path = "/game1Score/")
    String createReport(@RequestBody game1User game1User){
        if (game1User == null)
            return failure;
        game1ScoreboardRepositoryy.save(game1User);
        return success;
    }

    @DeleteMapping(path = "/game1Score/{id}")
    String Delete(@PathVariable long id){
       // game1User temp = game1ScoreboardRepositoryy.findById(id);
//        game1ScoreboardRepositoryy.deleteByUsername(temp.getUsername());
    game1ScoreboardRepositoryy.deleteById(id);
        return success;
    }




}
