package ru.pavlinina.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.pavlinina.ecommerce.models.User;
import ru.pavlinina.ecommerce.services.UserService;

/**
 * @author Sofia Pavlinina
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("signup")
    public ModelAndView signUp(User user) {
        ModelAndView mv = new ModelAndView("/index");
        userService.save(user);
        return mv;
    }

}
