package application.controllers;

import application.interfaces.IControllerWithPosts;
import application.models.Relationship;
import application.models.User;
import application.services.PostService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import application.services.UserService;
import org.springframework.web.bind.annotation.RequestParam;

import static application.security.SecurityConfig.getAuth;

@Controller
@RequestMapping("/profile")
public class ProfileController extends IControllerWithPosts {
    public ProfileController(UserService userService, AuthenticationManager authenticationManager, PostService postService) {
        super(userService, authenticationManager, postService);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String profilePage(Model model){
        model.addAttribute("posts", postService.getPostByUsername(getAuth()));
        model.addAttribute("user", userService.getUserByUsername(getAuth()));
        model.addAttribute("showForm", true);
        model.addAttribute("addContact", false);
        model.addAttribute("deleteContact", false);
        return "/profile";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String getProfileById(@PathVariable("username") String userUsername, Model model) {
        User user1 = userService.getUserByUsername(getAuth());
        User user2 = userService.getUserByUsername(userUsername);
        if(user2 == null) {
            return "redirect:/contacts";
        }
        model.addAttribute("posts", postService.getPostByUsername(user2.getUsername()));
        model.addAttribute("user", user2);
        model.addAttribute("showForm", false);
        if(user1.getUsername().equals(userUsername)){
            model.addAttribute("addContact", false);
            model.addAttribute("deleteContact", false);
        }
        else {
            Relationship relationship = userService.getRelationship(user1.getId(), user2.getId());
            model.addAttribute("addContact", relationship == null);
            model.addAttribute("deleteContact", relationship != null);
        }
        return "profile";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.POST)
    public String handleFormSubmit(Model model, @PathVariable("username") String userUsername, @RequestParam(required = false) String addContact, @RequestParam(required = false) String deleteContact) {
        User user1 = userService.getUserByUsername(getAuth());
        User user2 = userService.getUserByUsername(userUsername);
        if(user2 == null) {
            return "redirect:/contacts";
        }
        if (addContact != null) {
            System.out.println("ADD");
            userService.addRelation(user1.getId(), user2.getId());
        } else {
            System.out.println("DELETE");
            userService.deleteRelation(user1.getId(), user2.getId());
        }
        return "redirect:/profile/" + userUsername;
    }
}
