package com.example.konnect.friendsandgroups;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.konnect.R;
import com.example.konnect.helper.User;
import com.example.konnect.helper.WebSocketListener;
import com.example.konnect.helper.WebSocketManager;

import org.java_websocket.handshake.ServerHandshake;

/**
 * This class represents the ChatActivity and handles the chat functionality.
 *
 * @author Jayson Acosta
 */
public class ChatActivity extends AppCompatActivity implements WebSocketListener {

    TextView chatReceive;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatReceive = findViewById(R.id.chat);

        ImageButton imageButton = findViewById(R.id.ImageButton);
        imageButton.setOnClickListener(v -> finish());

        ImageView sendButton = findViewById(R.id.Send_Message);

        EditText messageText = findViewById(R.id.Chat_Message);

        String wsURL = "ws://coms-309-001.class.las.iastate.edu:8080/dm/" + User.getInstance().getUsername();
        WebSocketManager.getInstance().connectWebSocket(wsURL);
        WebSocketManager.getInstance().setWebSocketListener(this);

        sendButton.setOnClickListener(v -> {
            try {
                Bundle extras = getIntent().getExtras();
                assert extras != null;
                String user = extras.getString("user");
                WebSocketManager.getInstance().sendMessage("@" + user + messageText.getText().toString());
                messageText.setText("");
            }
            catch (Exception e) { Log.d("ExceptionSendMessage:", e.toString()); }
        });


    }

    /**
     * This method is called when the WebSocket connection is opened.
     *
     * @param handshakedata The handshake data for the WebSocket connection.
     */
    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {}

    /**
     * This method is called when a message is received from the WebSocket.
     *
     * @param message The message received from the WebSocket.
     */
    @Override
    public void onWebSocketMessage(String message) {
        runOnUiThread(() -> {
            String s = chatReceive.getText().toString();
            chatReceive.setText(String.format("%s\n%s", s, message));
        });
    }

    /**
     * This method is called when the WebSocket connection is closed.
     *
     * @param code   The code for the reason the WebSocket connection was closed.
     * @param reason The reason for the WebSocket connection being closed.
     * @param remote A flag indicating whether the connection was closed by the remote server or locally.
     */
    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) { Log.e("Websocket Closed", reason); }

    /**
     * This method is called when an error occurs in the WebSocket connection.
     *
     * @param ex The exception that occurred.
     */
    @Override
    public void onWebSocketError(Exception ex) { Log.e("Websocket Error", ex.toString()); }
}
