package ru.pavlinina.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.pavlinina.ecommerce.models.User;
import ru.pavlinina.ecommerce.services.CategoryService;
import ru.pavlinina.ecommerce.services.ProductService;
import ru.pavlinina.ecommerce.services.UserService;

/**
 * shop main page controller
 * @author Sofia Pavlinina
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;


    /**
     * method for getting shop main page
     * @param model data model
     * @return shop main page with list of products
     */
    @GetMapping({"index", "/"})
    public String index(Model model) {
        model.addAttribute("categoryList", categoryService.listCategory());
        model.addAttribute("productList", productService.listProduct());
        return "index";
    }

    /**
     * method for getting shop login page
     * @return shop login page with user data form
     */
    @GetMapping("login")
    public String login() {
        return "login";
    }

    /**
     * method for getting shop sing up page
     * @return shop sing up page with user data form
     */
    @GetMapping("signup")
    public String signup() {
        return "signup";
    }

    /**
     * method for posting new user data
     * @param user new entity to save in table
     * @return shop main page with list of products
     */
    @PostMapping("signup")
    public ModelAndView signUp(User user) {
        ModelAndView mv = new ModelAndView("/index");
        userService.save(user);
        mv.addObject("productList", productService.listProduct());
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }

    /**
     * method for getting shop page with all products
     * @param model data model
     * @return shop page with all products
     */
    @GetMapping("allProduct")
    public String allProduct(Model model) {
        model.addAttribute("productList", productService.listProduct());
        model.addAttribute("categoryList", categoryService.listCategory());
        return "index";
    }

    /**
     * method for getting shop page with products of necessary category
     * @param categoryId ID of necessary category
     * @return page with products of necessary category
     */
    @GetMapping("getProducts/{categoryId}")
    public ModelAndView getProductFromCategory(@PathVariable("categoryId")String categoryId) {
        ModelAndView mv = new ModelAndView("index");
        long categoryLongId = Long.parseLong(categoryId);
        System.out.println(categoryLongId);
        mv.addObject("productList", productService.findByCategory(categoryLongId));
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }

    /**
     * method for getting shop error page
     * @return shop error page
     */
    @GetMapping("error")
    public String error() {
        return "error";
    }


}
