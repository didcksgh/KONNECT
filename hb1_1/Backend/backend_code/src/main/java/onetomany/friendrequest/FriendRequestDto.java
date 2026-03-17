package onetomany.friendrequest;

public class FriendRequestDto {

    private int id; // Assuming the ID is an integer
    private String senderUsername;
    private String receiverUsername;
    private RequestStatus status;

    // Constructor
    public FriendRequestDto(int id, String senderUsername, String receiverUsername, RequestStatus status) {
        this.id = id;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
