//
//package onetomany.hobbies;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import onetomany.Users.User;
//
//@Entity
//public class Hobbies {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String name;
//
//    @Enumerated(EnumType.STRING) // This tells JPA to store the Enum as a String.
//    private HobbyType hobbyType; // This is the new field for the hobby type.
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonIgnore
//    private User user;
//
//
//
//    // Updated constructor with hobbyType
//    public Hobbies(String name,User user, HobbyType hobbyType ) {
//        this.name = name;
//        this.hobbyType = hobbyType;
//        this.user = user;
//    }
//
//    // Getters and setters
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public HobbyType getHobbyType() {
//        return hobbyType;
//    }
//
//    public void setHobbyType(HobbyType hobbyType) {
//        this.hobbyType = hobbyType;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//}
package onetomany.hobbies;

import jakarta.persistence.*;

import java.util.Set;
import onetomany.Users.User;

@Entity
@Table(name = "hobbies")
public class Hobbies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Enumerated(EnumType.STRING) // This tells JPA to store the Enum as a String.
    private HobbyType hobbyType; // This is the field for the hobby type.

    @ManyToMany
    @JoinTable(
            name = "user_hobbies",
            joinColumns = @JoinColumn(name = "hobby_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users; // Users associated with this hobby

    public Hobbies() {
    }

    public Hobbies(String name, HobbyType hobbyType) {
        this.name = name;
        this.hobbyType = hobbyType;


    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HobbyType getHobbyType() {
        return hobbyType;
    }

    public void setHobbyType(HobbyType hobbyType) {
        this.hobbyType = hobbyType;
    }
    public void addUser(User user){
        this.users.add(user);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    public void removeUser(User user){
        this.users.remove(user);
    }

    //    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
}
