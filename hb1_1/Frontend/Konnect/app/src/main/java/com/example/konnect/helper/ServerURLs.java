package com.example.konnect.helper;

public class ServerURLs {

    /*---------------------------------------------- SERVER URLS ----------------------------------------------*/
    /**
     * The URL of the server.
     */
    private static final String SERVER_URL = "http://coms-309-001.class.las.iastate.edu:8080/";
//    private static final String SERVER_URL = "https://df952b3b-a205-4a2f-a0e0-a0f471c5f2bb.mock.pstmn.io/";
//    private static final String SERVER_URL = "http://10.0.2.2:8080/";

    /**
     * The URL of the websocket
     */
    private static final String WEBSOCKET_URL = "ws://coms-309-001.class.las.iastate.edu:8080/";

    /**
     * The URL for user login.
     */
    private static String USERLOGIN_URL;

    /**
     * The URL for user information.
     */
    private static String USERINFO_URL;

    /**
     * The URL for friend requests.
     */
    private static String FRIENDREQUEST_URL;

    /**
     * The URL for profile pictures
     */
    private static String PFP_URL;
    /*---------------------------------------------- SERVER URL ----------------------------------------------*/

    /**
     * Gets the URL for the server.
     *
     * @return The URL for the server.
     */
    public static String getServerUrl(){ return SERVER_URL; }

    /*---------------------------------------------- USER LOGIN ----------------------------------------------*/
    /**
     * Sets the URL for user login with the provided username and password.
     */
    public static void setURL_UP(){ USERLOGIN_URL = SERVER_URL + "login/u/" + User.getInstance().getUsername() + "/" + User.getInstance().getPassword() + "/"; }

    /**
     * Sets the URL for user login with the provided email and password.
     */
    public static void setURL_EP(){ USERLOGIN_URL = SERVER_URL + "login/e/" + User.getInstance().getEmail() + "/" + User.getInstance().getPassword() + "/"; }

    /**
     * Sets the URL for friend requests with the provided username.
     */
    public static void setURL_FR(){ FRIENDREQUEST_URL = SERVER_URL + "friend-requests/list/" + User.getInstance().getUsername(); }

    /**
     * Sets the URL for user information with the provided ID.
     */
    public static void setURL_USERINFO(){ USERINFO_URL = SERVER_URL + "users/u/" + User.getInstance().getUsername(); }

    /**
     * Sets the URL for Profile Pictures
     */
    public static void setURL_PFP(){ PFP_URL = SERVER_URL + "users/" + User.getInstance().getUsername() + "/profile-image"; }
    /*-------------------------------------------- USER INFORMATION --------------------------------------------*/

    /**
     * Gets the URL for user login.
     *
     * @return The URL for user login.
     */
    public static String getURL_USERLOGIN(){ return USERLOGIN_URL; }

    /**
     * Gets the URL for user information.
     *
     * @return The URL for user information.
     */
    public static String getURL_USERINFO(){ return USERINFO_URL; }


    public static String getURL_FR(){ return FRIENDREQUEST_URL; }

    public static String getURL_PFP(){ return PFP_URL; }

    /*---------------------------------------------- WEBSOCKETS ----------------------------------------------*/
    /**
     * Gets the URL for Websockets.
     *
     * @return The URL for websockets
     */
    public static String getWEBSOCKET_URL(){ return WEBSOCKET_URL; }
}
