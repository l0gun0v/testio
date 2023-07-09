package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @GetMapping("/login")
    public String index() {
        return "login";
    }
    @PostMapping("/login")
    public String handleLogin(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {

        // If login is successful
        model.addAttribute("message", "Login successful!");

        // If login fails
        // model.addAttribute("message", "Invalid username and password.");

        return "img";
    }
}