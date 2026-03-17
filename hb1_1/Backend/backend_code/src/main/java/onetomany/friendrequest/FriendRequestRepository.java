package onetomany.friendrequest;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface FriendRequestRepository extends JpaRepository<FriendRequest, Integer> {
    List<FriendRequest> findByReceiverId(int receiverId);
    List<FriendRequest> findBySenderId(int senderId);
}





