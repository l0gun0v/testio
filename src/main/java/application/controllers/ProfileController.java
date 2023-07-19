package application.controllers;

import application.interfaces.IController;
import application.interfaces.IControllerWithPosts;
import application.models.User;
import application.services.PostService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import application.services.UserService;

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
        return "/profile";
    }
}
