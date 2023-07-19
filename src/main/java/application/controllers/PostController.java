package application.controllers;

import application.interfaces.IController;
import application.interfaces.IControllerWithPosts;
import application.models.Post;
import application.models.User;
import application.models.UserRepository;
import application.services.PostService;
import application.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static application.security.SecurityConfig.getAuth;

@Controller
@RequestMapping("")
public class PostController extends IControllerWithPosts {
    public PostController(UserService userService, AuthenticationManager authenticationManager, PostService postService) {
        super(userService, authenticationManager, postService);
    }

    @RequestMapping(value = "/home",method = RequestMethod.POST)
    public ModelAndView createPostFromHome(@RequestParam("postContent") String postContent, Model model){
        System.out.println(postContent);
        if(postContent.length() > 1000) {
            return new ModelAndView("redirect:/home");
        }
        User user = userService.getUserByUsername(getAuth());
        postService.createPost(user.getUsername(), postContent);
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/profile",method = RequestMethod.POST)
    public ModelAndView createPostFromProfile(@RequestParam("postContent") String postContent, Model model){
        System.out.println(postContent);
        if(postContent.length() > 1000) {
            return new ModelAndView("redirect:/profile");
        }
        User user = userService.getUserByUsername(getAuth());
        postService.createPost(user.getUsername(), postContent);
        return new ModelAndView("redirect:/profile");
    }

}
