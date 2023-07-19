package application.interfaces;

import application.services.PostService;
import application.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;

public class IControllerWithPosts extends IController{
    public final PostService postService;
    public IControllerWithPosts(UserService userService, AuthenticationManager authenticationManager, PostService postService) {
        super(userService, authenticationManager);
        this.postService = postService;
    }
}
