package onetomany.friendrequest;


import onetomany.Users.User;
import jakarta.persistence.*;


@Entity
@Table(name = "friend_requests")
public class FriendRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;


    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;


    @Enumerated(EnumType.STRING)
    private RequestStatus status;


    // Getters and setters
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public User getSender() {
        return sender;
    }


    public void setSender(User sender) {
        this.sender = sender;
    }


    public User getReceiver() {
        return receiver;
    }


    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }


    public RequestStatus getStatus() {
        return status;
    }


    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
