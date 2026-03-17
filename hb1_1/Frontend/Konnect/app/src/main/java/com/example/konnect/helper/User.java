package com.example.konnect.helper;

import android.app.AlertDialog;
import android.content.Context;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * This class represents a User object with various properties and methods.
 *
 * @author Jayson Acosta
 */
public class User implements Serializable {

    /*---------------------------------------------- USER VARIABLES ----------------------------------------------*/

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The email of the user.
     */
    private String email;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The gender of the user.
     */
    private String gender;

    /**
     * The bio of the user.
     */
    private String bio;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The ID of the user.
     */
    private String ID;

    /**
     * The int value id of the user
     */

    private String type;

    /**
     * The birthday of the user.
     */
    private String birthday;

    /**
     * The age of the user.
     */
    private String age;

    /**
     * The date the user joined the app.
     */
    private String joinDate;

    /**
     * The friend requests of the user, regardless of the status
     */
    private JSONArray friendRequests;

    /**
     * The Leaderboard Data for Tic Tac Toe
     */
    private JSONArray leaderboardData;

    /*---------------------------------------------- USER INSTANCE ----------------------------------------------*/

    /**
     * The singleton instance of the User class.
     */
    private static User instance;

    /**
     * Default constructor for the User class.
     */
    private User(){}

    /**
     * Gets the singleton instance of the User class.
     *
     * @return The instance of the User class.
     */
    public static synchronized User getInstance() {
        if (instance == null){ instance = new User(); }
        return instance;
    }

    public static synchronized void setInstance(User oldInstance) {
        instance = oldInstance;
    }

    public static synchronized boolean isDataValid(User instance){ return Stream.of(instance.username, instance.password, instance.email).allMatch(Objects::nonNull); }


    /*---------------------------------------------- SETTER METHODS ----------------------------------------------*/

    /**
     * Sets the username of the user.
     *
     * @param username The username to be set.
     */
    public User setUsername(String username) { this.username = username; return instance;}

    /**
     * Sets the email of the user.
     *
     * @param email The email to be set.
     */
    public User setEmail(String email) { this.email = email; return instance;}

    /**
     * Sets the ID of the user.
     *
     * @param ID The ID to be set.
     */
    public User setID(String ID) { this.ID = ID; return instance;}


    /**
     * Sets the password of the user.
     *
     * @param password The password to be set.
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Sets the name of the user.
     *
     * @param name The name to be set.
     */
    public User setName(String name) { this.name = name; return instance;}

    /**
     * Sets the age of the user.
     *
     * @param age The age to be set.
     */
    public User setAge(String age) { this.age = age; return instance;}

    /**
     * Sets the Join date of the user.
     *
     * @param joinDate The join date to be set.
     */
    public User setJoinDate(String joinDate){ this.joinDate = joinDate; return instance;}

    /**
     * Sets the gender of the user.
     *
     * @param gender The gender to be set.
     */
    public User setGender(String gender) { this.gender = gender; return instance;}

    /**
     * Sets the bio of the user.
     *
     * @param bio The bio to be set.
     */
    public User setBio(String bio) { this.bio = bio; return instance;}

    /**
     * Sets the birthday of the user.
     *
     * @param birthday The birthday to be set.
     */
    public User setBirthday(String birthday) { this.birthday = birthday; return instance;}

    /**
     *
     * @param type the type of user
     *
     */
    public User setType(String type){ this.type = type;return instance;}

    /** Sets the friend requests of the user
     *
     * @param friendRequests friend requests the user has received/sent
     */
    public void setFriendRequests(JSONArray friendRequests) { this.friendRequests = friendRequests;}

    /** Sets the Tic Tac Toe Leaderboard data
     *
     * @param leaderboardData the data from the leaderboard
     */
    public void setLeaderboardData(JSONArray leaderboardData) { this.leaderboardData = leaderboardData; }

    /*---------------------------------------------- GETTER METHODS ----------------------------------------------*/

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() { return username; }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() { return email; }

    /**
     * Gets the ID of the user.
     *
     * @return The ID of the user.
     */
    public String getID() { return ID; }

    /**
     * Gets the password of the user.
     *
     */
    public String getPassword() { return password; }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() { return name; }

    /**
     * Gets the age of the user.
     *
     * @return The age of the user.
     */
    public String getAge() { return age; }

    /**
     * Gets the join date of the user.
     *
     * @return The join date of the user.
     */
    public String getJoinDate() { return joinDate; }

    /**
     * Gets the gender of the user.
     *
     * @return The gender of the user.
     */
    public String getGender() { return gender; }

    /**
     * Gets the bio of the user.
     *
     * @return The bio of the user.
     */
    public String getBio() { return bio; }

    /**
     * Gets the birthday of the user.
     *
     * @return The birthday of the user.
     */
    public String getBirthday() { return birthday; }

    /**
     *
     * @return Type type of user
     */
    public String getType(){return type;}

    public JSONArray getFriendRequests() { return friendRequests; }

    public JSONArray getLeaderboardData() { return leaderboardData; }

    /*---------------------------------------------- SCREEN POPUPS ----------------------------------------------*/
//    /**
//     * Displays a toast message based on the provided error code and logs the error message.
//     *
//     * @param context   The context in which the toast message will be displayed.
//     * @param errorCode The error code indicating the type of error. 0 is a login error and 1 is a server error.
//     * @param error     The error message to be logged.
//     */
//
//    public static void toastError(Context context, int errorCode, String error){
//        int duration = Toast.LENGTH_LONG;
//        String text;
//        switch (errorCode){
//            case 0:
//                text = "There was an error while attempting to login";
//                break;
//            case 1:
//                text = "There is an error with the server";
//                break;
//            case 2:
//                text = error;
//                break;
//            default:
//                text = "Something went wrong";
//        }
//        Toast.makeText(context, text, duration).show();
//        Log.e("Error", error);
//    }
    public static void dialogError(Context context, String error){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(error)
                .setTitle("Error:");

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
