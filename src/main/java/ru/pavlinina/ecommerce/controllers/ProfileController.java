package ru.pavlinina.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.pavlinina.ecommerce.models.Product;
import ru.pavlinina.ecommerce.models.User;
import ru.pavlinina.ecommerce.services.ProductService;
import ru.pavlinina.ecommerce.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * profile controller
 * @author Sofia Pavlinina
 */
@Controller
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    /**
     * method for getting page with user's shopping cart
     * @param principal interface represents the abstract notion of a principal
     * @return page with user's shopping cart
     */
    @GetMapping("cart-product")
    public ModelAndView cartProduct(Principal principal) {
        ModelAndView mv = new ModelAndView("profile/cart-product");
        User user = userService.findByEmail(principal.getName());
        mv.addObject("user", user);
        int total = findSum(user);
        mv.addObject("total", total);
        return mv;
    }

    /**
     * method for getting total shopping cart price
     * @param user entity, which product list is needed
     * @return summary of product prices in shopping cart
     */
    private int findSum(User user) {
        List<Product> productList = user.getProductList();
        int sum =0;
        for (Product p : productList) {
            sum += p.getProductPrice();
        }
        return sum;
    }

    /**
     * method for adding product to shopping cart
     * @param productId ID of necessary product
     * @param principal interface represents the abstract notion of a principal
     * @return page with user's shopping cart with added product
     */
    @GetMapping("addToCart/{productId}")
    public ModelAndView addToCart(@PathVariable("productId")String productId, Principal principal) {
        ModelAndView mv = new ModelAndView("profile/cart-product");
        User user = userService.findByEmail(principal.getName());
        long productLongId = Long.parseLong(productId);
        Product product = productService.getProductById(productLongId).get();

        List<Product> productList = new ArrayList<Product>();
        productList.add(product);
        user.setProductList(productList);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        product.setUserList(userList);

        userService.update(user);
        productService.addProduct(product);

        int total = findSum(user);
        mv.addObject("total", total);

        mv.addObject("user", user);

        return mv;
    }

}
