package com.example.konnect.friendsandgroups;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.konnect.R;
import com.example.konnect.helper.User;
import com.example.konnect.helper.WebSocketListener;
import com.example.konnect.helper.WebSocketManager;

import org.java_websocket.handshake.ServerHandshake;

public class GroupChatActivity extends AppCompatActivity implements WebSocketListener {

    private Button sendBtn;
    private EditText msgEtx;
    private TextView msgTv;
    String serverUrl = "ws://coms-309-001.class.las.iastate.edu:8080/chat/";

    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupchat);

        /* initialize UI elements */
        sendBtn = (Button) findViewById(R.id.sendBtn);
        msgEtx = (EditText) findViewById(R.id.msgEdt);
        msgTv = (TextView) findViewById(R.id.tx1);

        /* connect this activity to the websocket instance */
        WebSocketManager.getInstance().connectWebSocket(serverUrl + User.getInstance().getUsername());
        WebSocketManager.getInstance().setWebSocketListener(GroupChatActivity.this);

        /* send button listener */
        sendBtn.setOnClickListener(v -> {
            try {
                // send message
                String message = msgEtx.getText().toString();
                if (message.startsWith("@")) {
                    String destUsername = message.split("\\s")[0].substring(1);
                    String actualMessage = message.substring(destUsername.length() + 2);
                    WebSocketManager.getInstance().sendMessage("@ " + destUsername + " " + actualMessage);
                } else {
                    WebSocketManager.getInstance().sendMessage(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onWebSocketMessage(String message) {
        runOnUiThread(() -> {
            String s = msgTv.getText().toString();
            msgTv.setText(s + "\n" + message);
        });
    }

    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {
        String closedBy = remote ? "server" : "local";
        runOnUiThread(() -> {
            String s = msgTv.getText().toString();
            msgTv.setText(s + "---\nconnection closed by " + closedBy + "\nreason: " + reason);
        });
    }

    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {}

    @Override
    public void onWebSocketError(Exception ex) {}
}
