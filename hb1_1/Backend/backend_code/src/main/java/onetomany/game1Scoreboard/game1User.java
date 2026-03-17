package onetomany.game1Scoreboard;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import onetomany.Users.User;

import java.util.Date;
import java.util.List;

@Entity
public class game1User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;



    private String username;
    private int score;

    public int getId(){
        return this.id;
    }
    public void setId(int newId){
        this.id=newId;
    }

    public int getScore() {
        return score;
    }

    public String getUsername() {
        return username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
