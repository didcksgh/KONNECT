package coms309;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Hello and welcome to COMS 309";
    }

    @GetMapping("/{name}")

    public String welcome(@PathVariable String name) {
        return "Hello and welcome to COMS 309: " + name;
    }
    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return "Greetings from COMS 309: " + name;
    }
    @GetMapping("/welcome/{name}")
    public String welcomeName(@PathVariable String name) {
        return "Hello and welcome to COMS 309: " + name;
    }
    @GetMapping("/multiply/{a}/{b}")
    public String multiply(@PathVariable int a, @PathVariable int b) {
        int result = a * b;
        return "The result of " + a + " multiplied by " + b + " is " + result;
    }
    //http://localhost:8080/multiply/3/4
    //http://localhost:8080/greet/Alice
    //http://localhost:8080/welcome/Alice
}
