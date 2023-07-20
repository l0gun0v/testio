package application.controllers;

import application.interfaces.IController;
import application.models.User;
import application.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        //model.addAttribute("newPassword");
        return "/profileSettings";
    }

    @RequestMapping(value = "/profile/settings",method = RequestMethod.POST)
    public ModelAndView modifyUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user, @RequestParam("newPassword") String newPassword){
        User userData = userService.getUserByUsername(getAuth());

        if(!user.getEmail().equals("")){
            userData.setEmail(user.getEmail());
        }
        if(!user.getGender().equals("")){
            userData.setGender(user.getGender());
        }
        if(!user.getCountry().equals("")){
            userData.setCountry(user.getCountry());
        }
        userData.setDateOfBirth(user.getDateOfBirth());
        System.out.println(user.getDateOfBirth());
        if(!user.getLastName().equals("")){
            userData.setLastName(user.getLastName());
        }
        if(!user.getFirstName().equals("")){
            userData.setLastName(user.getFirstName());
        }
        if(!user.getUsername().equals("")) {
            User tempUser = userService.getUserByUsername(user.getUsername());
            if (tempUser == null) {
                userData.setUsername(user.getUsername());
            }
        }
        if(!newPassword.equals("")) {
            if(user.getPassword().equals(userData.getPassword())){
                userData.setPassword(newPassword);
            }
        }
        userService.saveUser(userData);
        return new ModelAndView("redirect:/profile/settings");
    }

}
