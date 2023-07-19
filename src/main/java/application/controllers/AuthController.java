package application.controllers;

import application.models.UserRepository;
import application.interfaces.IController;
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

@Controller
@RequestMapping("")
public class AuthController extends IController{
    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        super(userService, authenticationManager);
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String blank(){
        return "redirect:/home";
    }
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }
    private void setToken(HttpServletRequest request, User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,securityContext);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView createNewUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user")User user){
        User someUser = userService.getUserByUsername(user.getEmail());
        if(someUser != null){
            return new ModelAndView("redirect:/register?error");
        }
        try {
            user.setRole("USER");

            User newUser = userService.createUser(user);
            if(newUser == null){
                return new ModelAndView("redirect:/register?error");
            }
            setToken(request, user);
            return new ModelAndView("redirect:/home");

        } catch (Exception e){
            return new ModelAndView("redirect:/register?error");
        }

    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(null);
        return "redirect:/login";
    }
}

