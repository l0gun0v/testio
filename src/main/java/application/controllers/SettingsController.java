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
@RequestMapping("")
public class SettingsController extends IController {
    public SettingsController(UserService userService, AuthenticationManager authenticationManager) {
        super(userService, authenticationManager);
    }

    @RequestMapping(value = "/profile/settings",method = RequestMethod.GET)
    public String homePage(Model model){
        model.addAttribute("user",  userService.getUserByUsername(getAuth()));
        return "/profileSettings";
    }

    @RequestMapping(value = "/profile/settings",method = RequestMethod.POST)
    public ModelAndView modifyUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user){
        User userData = userService.getUserByUsername(getAuth());
        System.out.println(user.getEmail());
        return new ModelAndView("redirect:/profile/settings");
    }

}
