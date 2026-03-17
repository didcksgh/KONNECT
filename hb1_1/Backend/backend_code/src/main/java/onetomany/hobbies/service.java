package onetomany.hobbies;
import onetomany.Users.User;
import onetomany.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

public class service {
    @Autowired
    UserRepository userRepository;

    // This method is just a conceptual example
    public Set<User> findUsersWithHobby(String hobbyName) {
        Set<User> matchedUsers = new HashSet<>();
        // Logic to fetch users with the specified hobbyName
        // This requires custom query methods in UserRepository or HobbiesRepository
        return matchedUsers;
    }

}

