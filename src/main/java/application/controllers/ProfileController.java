package application.controllers;

import application.interfaces.IControllerWithPosts;
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
        User user = userService.getUserByUsername(userUsername);
        if(user == null || user.getUsername().equals(getAuth())) {
            return "redirect:/profile";
        }
        model.addAttribute("posts", postService.getPostByUsername(user.getUsername()));
        model.addAttribute("user", user);
        model.addAttribute("showForm", false);
        model.addAttribute("addContact", false);
        model.addAttribute("deleteContact", true);
        return "profile";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.POST)
    public String handleFormSubmit(Model model, @PathVariable("username") String userUsername, @RequestParam(required = false) Boolean addContact, @RequestParam(required = false) Boolean deleteContact) {
        if (addContact != null && addContact) {
            System.out.println("ADD");
            //TODO add relation
        } else {
            System.out.println("DELETE");
            //TODO delete relation
        }
        return "redirect:/profile/" + userUsername;
    }
}
