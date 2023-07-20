package application.controllers;

import application.interfaces.IController;
import application.interfaces.IControllerWithPosts;
import application.models.UserRepository;
import application.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import application.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import application.services.UserService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static application.security.SecurityConfig.getAuth;

@Controller
@RequestMapping("/home")
public class HomeController extends IControllerWithPosts {

    public HomeController(UserService userService, AuthenticationManager authenticationManager, PostService postService) {
        super(userService, authenticationManager, postService);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String homePage(Model model){
        User user = userService.getUserByUsername(getAuth());
        List<User> contacts = userService.getAllContacts(user.getId());
        List<String> contactsUsernames = new ArrayList<>();
        for(User u : contacts){
            contactsUsernames.add(u.getUsername());
        }
        model.addAttribute("posts", postService.allContactsPost(contactsUsernames));
        return "/home";
    }
}
