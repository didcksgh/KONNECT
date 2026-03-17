//package onetomany.hobbies;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import java.util.Optional;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//@RestController
//public class HobbiesController {
//
//    @Autowired
//    private HobbiesRepository hobbiesRepository;
//
//    private final String success = "{\"message\":\"success\"}";
//    private final String failure = "{\"message\":\"failure\"}";
//
//    @GetMapping(path = "/hobbies")
//    public List<Hobbies> getAllHobbies() {
//        return hobbiesRepository.findAll();
//    }
//
//    @GetMapping(path = "/hobbies/{id}")
//    public Hobbies getHobbyById(@PathVariable int id) {
//        // Assuming findById is changed to return Optional<Hobbies>
//        return hobbiesRepository.findById(id).orElse(null);
//    }
//
//    @PostMapping(path = "/hobbies")
//    public String createHobby(@RequestBody Hobbies hobby) {
//        if (hobby == null) {
//            return failure;
//        }
//        hobbiesRepository.save(hobby);
//        return success;
//    }
//
//    @PutMapping(path = "/hobbies/{id}")
//    public Hobbies updateHobby(@PathVariable int id, @RequestBody Hobbies request) {
//        return hobbiesRepository.findById(id).map(hobby -> {
//            hobby.setName(request.getName());
//            hobby.setHobbyType(request.getHobbyType()); // Updating hobby type
//            hobby.setUser(request.getUser());
//            hobbiesRepository.save(hobby);
//            return hobby;
//        }).orElse(null); // If the hobby doesn't exist, return null
//    }
//
//    @DeleteMapping(path = "/hobbies/{id}")
//    public String deleteHobby(@PathVariable int id) {
//        if (!hobbiesRepository.existsById(id)) {
//            return failure;
//        }
//        hobbiesRepository.deleteById(id);
//        return success;
//    }
//}
package onetomany.hobbies;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import onetomany.hobbies.HobbiesRepository;
import onetomany.hobbies.Hobbies;

@RestController
public class HobbiesController {

    @Autowired
    private HobbiesRepository hobbiesRepository;

    private final String success = "{\"message\":\"success\"}";
    private final String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/hobbies")
    public List<Hobbies> getAllHobbies() {
        return hobbiesRepository.findAll();
    }

    @GetMapping(path = "/hobbies/{id}")
    public Hobbies getHobbyById(@PathVariable int id) {
        return hobbiesRepository.findById(id).orElse(null);
    }

    @PostMapping(path = "/hobbies")
    public String createHobby(@RequestBody Hobbies hobby) {
        if (hobby == null) {
            return failure;
        }
        hobbiesRepository.save(hobby);
        return success;
    }

    @PutMapping(path = "/hobbies/{id}")
    public String updateHobby(@PathVariable int id, @RequestBody Hobbies request) {
        return hobbiesRepository.findById(id).map(hobby -> {
            hobby.setName(request.getName());
            hobby.setHobbyType(request.getHobbyType()); // Assume HobbyType field exists and is correctly handled
            // Removed setUser() call as it's no longer relevant in a Many-to-Many relationship context
            hobbiesRepository.save(hobby);
            return success;
        }).orElse(failure); // If the hobby doesn't exist, return failure
    }

    @DeleteMapping(path = "/hobbies/{id}")
    public String deleteHobby(@PathVariable int id) {
        if (!hobbiesRepository.existsById(id)) {
            return failure;
        }
        hobbiesRepository.deleteById(id);
        return success;
    }
}
