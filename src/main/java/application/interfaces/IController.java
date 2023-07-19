package application.interfaces;

import application.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;

@Controller
public class IController {
     public final UserService userService;
     public final AuthenticationManager authenticationManager;

    public IController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }
}
