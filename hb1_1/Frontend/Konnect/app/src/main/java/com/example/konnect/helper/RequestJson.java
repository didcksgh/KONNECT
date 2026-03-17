package com.example.konnect.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.konnect.dashboard.DashboardActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestJson {

    /*---------------------------------------------- GET REQUESTS ----------------------------------------------*/

    public static synchronized JsonObjectRequest login(Activity activity, Context context, RequestQueue queue, ProgressBar progressBar) {
        return new JsonObjectRequest(Request.Method.GET, ServerURLs.getURL_USERLOGIN(), null, response -> {
            try {
                User.getInstance().setID(response.getString("id"))
                        .setEmail(response.getString("email"))
                        .setUsername(response.getString("userName"))
                        .setType(response.getString("type"));
                        //.setType(response.getString("name"));

            } catch (JSONException error) {
                User.dialogError(context, error.toString());
            }
            queue.add(viewProfile(context));
            queue.add(friendRequests(activity, context, progressBar));
            queue.add(getScoreBoard(context));
        }, error -> {
            User.dialogError(context, error.toString());
            progressBar.setVisibility(View.GONE);
        });
    }

    public static synchronized JsonObjectRequest viewProfile(Context context) {
        ServerURLs.setURL_USERINFO();
        return new JsonObjectRequest(Request.Method.GET, ServerURLs.getURL_USERINFO(), null, response -> {
            try {
                User.getInstance().setName(response.getString("name"))
                        .setBio(response.getString("userBio"))
                        .setGender(response.getString("gender"))
                        .setBirthday(response.getString("birthday"))
                        .setJoinDate(response.getString("joiningDate"))
                        .setAge(response.getString("age"));
            } catch (JSONException error) {
                User.dialogError(context, error.toString());
            }
        }, error -> User.dialogError(context, error.toString()));
    }

    public static synchronized JsonArrayRequest friendRequests(Activity activity, Context context, ProgressBar progressBar) {
        ServerURLs.setURL_FR();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, ServerURLs.getURL_FR(), null, response -> User.getInstance().setFriendRequests(response), error -> {
            User.dialogError(context, error.toString());
            progressBar.setVisibility(View.GONE);
        });
        progressBar.setVisibility(View.GONE);
        activity.startActivity(new Intent(context, DashboardActivity.class));
        activity.finish();
        return jsonArrayRequest;
    }

    /*---------------------------------------------- POST REQUESTS ---------------------------------------------*/

    public static synchronized JsonObjectRequest friendRequestStatusUpdate(Context context, JSONObject params, String path, int id) {
        String url = String.format("%sfriend-requests/%s/%s", ServerURLs.getServerUrl(), path, id);
        return new JsonObjectRequest(Request.Method.POST, url, params, response -> Toast.makeText(context, "Friend request sent successfully", Toast.LENGTH_SHORT).show(), error -> {});
    }

//    public static synchronized JsonObjectRequest sendFriendRequest(Context context, JSONObject params) {
//        String url = String.format("%sfriend-requests/send", ServerURLs.getServerUrl());
//
//        return new JsonObjectRequest(Request.Method.POST, url, params, response -> {
//            try {
//                Toast.makeText(context, response.getString("message"), Toast.LENGTH_SHORT).show();
//            } catch (JSONException e) {
//                throw new RuntimeException(e);
//            }
//        }, error -> Log.e("Volley", error.toString()));
//    }

    public static synchronized StringRequest sendFriendRequest(Context context, String receiverUsername) {
        String url = String.format("%sfriend-requests/send", ServerURLs.getServerUrl());

        return new StringRequest(Request.Method.POST, url, response -> Toast.makeText(context, response, Toast.LENGTH_SHORT).show() , error -> Log.e("Volley", error.toString())){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("senderUsername", User.getInstance().getUsername());
                params.put("receiverUsername", receiverUsername);
                return params;
            }
        };
    }
//    public static synchronized JsonObjectRequest updateScoreboard(Activity activity, Context context, JSONObject params, String game) {
//        String url = String.format("%s%s", ServerURLs.getServerUrl(), game);
//
//        return new JsonObjectRequest(Request.Method.POST, url, params, response -> {
//            activity.finish();
//        }, error -> {
//            Log.e("Volley", error.toString());
//            activity.finish();
//        });
//    }

    public static synchronized StringRequest updateScoreBoard(Activity activity, Context context, String game) {
        String url = String.format("%s%s", ServerURLs.getServerUrl(), game);

        return new StringRequest(Request.Method.POST, url, response -> activity.finish(), error -> {
            Log.e("Volley", error.toString());
            activity.finish();
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", User.getInstance().getUsername());
                params.put("result", "win");
                return params;
            }
        };
    }

    public static synchronized JsonArrayRequest getScoreBoard(Context context){
        String url = ServerURLs.getServerUrl() + "api/tictactoe/leaderboard";
        return new JsonArrayRequest(Request.Method.GET, url, null, response -> User.getInstance().setLeaderboardData(response), error -> Log.e("Volley LB error",error.toString()));
    }

    public static synchronized StringRequest createGroup(Context context, String name) {
        String url = String.format("%sgroups/create/%s", ServerURLs.getServerUrl(), name);

        return new StringRequest(Request.Method.POST, url, response -> Toast.makeText(context, response, Toast.LENGTH_SHORT).show(), error -> Log.e("Volley", error.toString()));
    }

    public static synchronized StringRequest addUser(Context context, String name, String user) {
        String url = String.format("%sgroups/addUser/%s/%s", ServerURLs.getServerUrl(), user, name);

        return new StringRequest(Request.Method.POST, url, response -> Toast.makeText(context, response, Toast.LENGTH_SHORT).show(), error -> Log.e("Volley", error.toString()));
    }



//    public static synchronized ImageRequest getProfilePicture(Context context){
//        String url = String.format("%s%s", ServerURLs.getURL_USERINFO(),"/profile-image" );
//        return new ImageRequest()
//    }
}
