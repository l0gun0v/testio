package application.controllers;

import application.interfaces.IController;
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
public class PostController extends IController {
    public final PostService postService;
    public PostController(UserService userService, AuthenticationManager authenticationManager, PostService postService) {
        super(userService, authenticationManager);
        this.postService = postService;

    }
    @RequestMapping(value = "/home",method = RequestMethod.POST)
    public ModelAndView createPost(@RequestParam("postContent") String postContent, Model model){
        System.out.println(postContent);
        if(postContent.length() > 1000) {
            return new ModelAndView("redirect:/home");
        }
        User user = userService.getUserByEmail(getAuth());
        postService.createPost(user.getId(), postContent);
        return new ModelAndView("redirect:/home");
    }

}
