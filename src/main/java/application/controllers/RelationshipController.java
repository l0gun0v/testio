package application.controllers;

import application.interfaces.IController;
import application.models.User;
import application.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static application.security.SecurityConfig.getAuth;

@Controller
@RequestMapping("/contacts")
public class RelationshipController extends IController {

    public RelationshipController(UserService userService, AuthenticationManager authenticationManager) {
        super(userService, authenticationManager);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String profilePage(Model model){
        model.addAttribute("friends", userService.getAllUsers());
        return "/contacts";
    }

}
