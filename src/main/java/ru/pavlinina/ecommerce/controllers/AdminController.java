package ru.pavlinina.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.pavlinina.ecommerce.services.UserService;

/**
 * administrator controller
 * @author Sofia Pavlinina
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;


    /**
     * method for getting administrator main page
     * @return administrator main page with list of actions
     */
    @GetMapping("index")
    public String index() {
        return "admin/index";
    }

    /**
     * method for getting administrator page with list of users
     * @param model data model
     * @return administrator page with list of users
     */
    @GetMapping("user-list")
    public String userList(Model model) {
        model.addAttribute("userList", userService.findAllUser());
        return "admin/user-list";
    }

    /**
     * method for getting administrator page with list of users without necessary user
     * @param userId ID of user to remove
     * @return administrator page with list of users
     */
    @GetMapping("delete-User/{userId}")
    public ModelAndView deleteUser(@PathVariable("userId")String userId) {
        ModelAndView mv = new ModelAndView("admin/user-list");
        userService.deleteUser(Long.parseLong(userId));
        mv.addObject("userList", userService.findAllUser());
        return mv;
    }

    /**
     * method for getting administrator page with  form for new user
     * @return administrator page with form for new user
     */
    @GetMapping("add-user")
    public String addUser() {
        return "admin/add-user";
    }

}
