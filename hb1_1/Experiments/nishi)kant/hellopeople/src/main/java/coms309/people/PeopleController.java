
package coms309.people;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class PeopleController {

    private HashMap<String, Person> peopleList = new HashMap<>();

    @GetMapping("/people")
    public HashMap<String, Person> getAllPersons() {
        return peopleList;
    }

    @PostMapping("/people")
    public String createPerson(@RequestBody Person person) {
        peopleList.put(person.getFirstName(), person);
        return "New person " + person.getFirstName() + " saved.";
    }

    @GetMapping("/people/{firstName}")
    public Person getPerson(@PathVariable String firstName) {
        return peopleList.get(firstName);
    }

    @PutMapping("/people/{firstName}")
    public Person updatePerson(@PathVariable String firstName, @RequestBody Person person) {
        peopleList.replace(firstName, person);
        return peopleList.get(firstName);
    }

    @DeleteMapping("/people/{firstName}")
    public HashMap<String, Person> deletePerson(@PathVariable String firstName) {
        peopleList.remove(firstName);
        return peopleList;
    }
}
