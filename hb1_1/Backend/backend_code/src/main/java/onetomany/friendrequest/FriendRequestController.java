//package onetomany.friendrequest;
//
//import onetomany.Users.User;
//import onetomany.Users.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/friend-requests")
//public class FriendRequestController {
//
//    @Autowired
//    private FriendRequestRepository friendRequestRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/send")
//    public ResponseEntity<String> sendFriendRequest(@RequestParam String senderUsername, @RequestParam String receiverUsername) {
//        User sender = userRepository.findUserByUsername(senderUsername);
//        User receiver = userRepository.findUserByUsername(receiverUsername);
//
//        if (sender == null || receiver == null) {
//            return ResponseEntity.badRequest().body("{\"message\":\"One or both users not found\"}");
//        }
//
//        FriendRequest friendRequest = new FriendRequest();
//        friendRequest.setSender(sender);
//        friendRequest.setReceiver(receiver);
//        friendRequest.setStatus(RequestStatus.PENDING);
//        friendRequestRepository.save(friendRequest);
//        return ResponseEntity.ok("{\"message\":\"Friend request sent successfully\"}");
//    }
//
//    @PostMapping("/accept/{requestId}")
//    public ResponseEntity<String> acceptFriendRequest(@PathVariable int requestId) {
//        Optional<FriendRequest> friendRequestOpt = friendRequestRepository.findById(requestId);
//        if (!friendRequestOpt.isPresent()) {
//            return ResponseEntity.badRequest().body("{\"message\":\"Friend request not found\"}");
//        }
//
//        FriendRequest friendRequest = friendRequestOpt.get();
//        friendRequest.setStatus(RequestStatus.ACCEPTED);
//        friendRequestRepository.save(friendRequest);
//        return ResponseEntity.ok("{\"message\":\"Friend request accepted\"}");
//    }
//
//    @PostMapping("/decline/{requestId}")
//    public ResponseEntity<String> declineFriendRequest(@PathVariable int requestId) {
//        Optional<FriendRequest> friendRequestOpt = friendRequestRepository.findById(requestId);
//        if (!friendRequestOpt.isPresent()) {
//            return ResponseEntity.badRequest().body("{\"message\":\"Friend request not found\"}");
//        }
//
//        FriendRequest friendRequest = friendRequestOpt.get();
//        friendRequest.setStatus(RequestStatus.DECLINED);
//        friendRequestRepository.save(friendRequest);
//        return ResponseEntity.ok("{\"message\":\"Friend request declined\"}");
//    }
//
//    @DeleteMapping("/cancel/{requestId}")
//    public ResponseEntity<String> cancelFriendRequest(@PathVariable int requestId) {
//        Optional<FriendRequest> friendRequestOpt = friendRequestRepository.findById(requestId);
//        if (!friendRequestOpt.isPresent()) {
//            return ResponseEntity.badRequest().body("{\"message\":\"Friend request not found\"}");
//        }
//
//        friendRequestRepository.delete(friendRequestOpt.get());
//        return ResponseEntity.ok("{\"message\":\"Friend request cancelled\"}");
//    }
//
//    @GetMapping("/list/{username}")
//    public ResponseEntity<List<FriendRequestDto>> listFriendRequests(@PathVariable String username) {
//        User user = userRepository.findUserByUsername(username);
//        if (user == null) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        List<FriendRequest> receivedRequests = friendRequestRepository.findByReceiverId(user.getId());
//        List<FriendRequest> sentRequests = friendRequestRepository.findBySenderId(user.getId());
//
//        List<FriendRequestDto> dtos = receivedRequests.stream()
//                .map(fr -> new FriendRequestDto(fr.getId(), fr.getSender().getUsername(), fr.getReceiver().getUsername(), fr.getStatus()))
//                .collect(Collectors.toList());
//
//        dtos.addAll(sentRequests.stream()
//                .map(fr -> new FriendRequestDto(fr.getId(), fr.getSender().getUsername(), fr.getReceiver().getUsername(), fr.getStatus()))
//                .collect(Collectors.toList()));
//
//        return ResponseEntity.ok(dtos);
//    }
//}
package onetomany.friendrequest;

import onetomany.Users.User;
import onetomany.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/friend-requests")
public class FriendRequestController {

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/send")
    public ResponseEntity<String> sendFriendRequest(@RequestParam String senderUsername, @RequestParam String receiverUsername) {
        User sender = userRepository.findUserByUsername(senderUsername);
        User receiver = userRepository.findUserByUsername(receiverUsername);

        if (sender == null || receiver == null) {
            return ResponseEntity.badRequest().body("{\"message\":\"One or both users not found\"}");
        }

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSender(sender);
        friendRequest.setReceiver(receiver);
        friendRequest.setStatus(RequestStatus.PENDING);
        friendRequestRepository.save(friendRequest);
        return ResponseEntity.ok("{\"message\":\"Friend request sent successfully\"}");
    }

    @PostMapping("/accept/{requestId}")
    public ResponseEntity<String> acceptFriendRequest(@PathVariable int requestId) {
        Optional<FriendRequest> friendRequestOpt = friendRequestRepository.findById(requestId);
        if (!friendRequestOpt.isPresent()) {
            return ResponseEntity.badRequest().body("{\"message\":\"Friend request not found\"}");
        }

        FriendRequest friendRequest = friendRequestOpt.get();
        friendRequest.setStatus(RequestStatus.ACCEPTED);
        friendRequestRepository.save(friendRequest);
        return ResponseEntity.ok("{\"message\":\"Friend request accepted\"}");
    }

    @PostMapping("/decline/{requestId}")
    public ResponseEntity<String> declineFriendRequest(@PathVariable int requestId) {
        Optional<FriendRequest> friendRequestOpt = friendRequestRepository.findById(requestId);
        if (!friendRequestOpt.isPresent()) {
            return ResponseEntity.badRequest().body("{\"message\":\"Friend request not found\"}");
        }

        FriendRequest friendRequest = friendRequestOpt.get();
        friendRequest.setStatus(RequestStatus.DECLINED);
        friendRequestRepository.save(friendRequest);
        return ResponseEntity.ok("{\"message\":\"Friend request declined\"}");
    }

    @DeleteMapping("/cancel/{requestId}")
    public ResponseEntity<String> cancelFriendRequest(@PathVariable int requestId) {
        Optional<FriendRequest> friendRequestOpt = friendRequestRepository.findById(requestId);
        if (!friendRequestOpt.isPresent()) {
            return ResponseEntity.badRequest().body("{\"message\":\"Friend request not found\"}");
        }

        friendRequestRepository.delete(friendRequestOpt.get());
        return ResponseEntity.ok("{\"message\":\"Friend request cancelled\"}");
    }

    @GetMapping("/list/{username}")
    public ResponseEntity<List<FriendRequestDto>> listFriendRequests(@PathVariable String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        List<FriendRequest> receivedRequests = friendRequestRepository.findByReceiverId(user.getId());
        List<FriendRequest> sentRequests = friendRequestRepository.findBySenderId(user.getId());

        List<FriendRequestDto> dtos = receivedRequests.stream()
                .map(fr -> new FriendRequestDto(fr.getId(), fr.getSender().getUsername(), fr.getReceiver().getUsername(), fr.getStatus()))
                .collect(Collectors.toList());

        dtos.addAll(sentRequests.stream()
                .map(fr -> new FriendRequestDto(fr.getId(), fr.getSender().getUsername(), fr.getReceiver().getUsername(), fr.getStatus()))
                .collect(Collectors.toList()));

        return ResponseEntity.ok(dtos);
    }
}
