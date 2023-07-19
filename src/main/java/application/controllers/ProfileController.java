package application.controllers;

import application.interfaces.IController;
import application.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import application.services.UserService;

import static application.security.SecurityConfig.getAuth;

@Controller
@RequestMapping("/profile")
public class ProfileController extends IController {
    public ProfileController(UserService userService, AuthenticationManager authenticationManager) {
        super(userService, authenticationManager);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String profilePage(Model model){
        //model.addAttribute("username", getAuth());
        System.out.println(getAuth());
        User user = userService.getUserByEmail(getAuth());
        System.out.println(user.getId());
        return "/profile";
    }
}
