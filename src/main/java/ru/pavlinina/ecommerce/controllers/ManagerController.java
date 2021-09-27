package ru.pavlinina.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.pavlinina.ecommerce.models.Category;
import ru.pavlinina.ecommerce.models.Product;
import ru.pavlinina.ecommerce.services.CategoryService;
import ru.pavlinina.ecommerce.services.FileUploadService;
import ru.pavlinina.ecommerce.services.ProductService;

/**
 * manager controller
 * @author Sofia Pavlinina
 */
@Controller
@RequestMapping("manager")
public class ManagerController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * method for getting manager main page
     * @return manager main page with list of actions
     */
    @GetMapping("index")
    public String index() {
        return "manager/index";
    }

    //	Category--------------------------------------------------
    /**
     * method for getting page with categories table
     * @return page with categories table
     */
    @GetMapping("category-form")
    public ModelAndView listCategory() {
        ModelAndView mv = new ModelAndView("manager/category-form");
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }

    /**
     * method for adding new category to list
     * @param category new entity to save in table
     * @return page with categories table with added category
     */
    @PostMapping("add-category")
    public ModelAndView addCategory(Category category) {
        ModelAndView mv = new ModelAndView("manager/category-form");
        categoryService.addCategory(category);
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }

    /**
     * method for removing category from list
     * @param categoryId ID of category to delete
     * @return page with categories table without removed category
     */
    @GetMapping("delete-Category/{categoryId}")
    public ModelAndView deleteCategory(@PathVariable("categoryId")String categoryId) {
        ModelAndView mv = new ModelAndView("manager/category-form");
        categoryService.deleteCategory(Long.parseLong(categoryId));
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }

    /**
     * method for updating category from list
     * @param categoryId ID of category to update
     * @return page with categories table with updated category
     */
    @GetMapping("updateCategory/{categoryId}")
    public ModelAndView updateCategory(@PathVariable("categoryId")String categoryId) {
        ModelAndView mv = new ModelAndView("manager/updateCategory");
        mv.addObject("Category", categoryService.getCategory(Long.parseLong(categoryId)).get());
        return mv;
    }


    //	Product--------------------------------------------------
    /**
     * method for getting page with products table
     * @return page with products table
     */
    @GetMapping("product-form")
    public ModelAndView listProduct() {
        ModelAndView mv = new ModelAndView("manager/product-form");
        mv.addObject("categoryList", categoryService.listCategory());
        mv.addObject("productList", productService.listProduct());
        return mv;
    }

    /**
     * method for posting new product to list
     * @param product new entity to save in table
     * @param file image file for new product
     * @return page with products table with added product
     */
    @PostMapping("add-product")
    public ModelAndView addProduct(Product product, @RequestParam("file") MultipartFile file) {
        ModelAndView mv = new ModelAndView("manager/product-form");
        System.out.println("file: " + file.getOriginalFilename());
        String filePath = fileUploadService.upload(file);
        product.setImage(filePath);
        System.out.println(product.getImage());
        productService.addProduct(product);
        mv.addObject("productList", productService.listProduct());
        return mv;
    }

    /**
     * method for getting page with products table without deleted product
     * @param productId ID of product to remove
     * @return page with products table without deleted product
     */
    @GetMapping("delete-Product/{productId}")
    public ModelAndView deleteProduct(@PathVariable("productId")String productId) {
        ModelAndView mv = new ModelAndView("manager/product-form");
        productService.deleteProduct(Long.parseLong(productId));
        mv.addObject("productList", productService.listProduct());
        return mv;
    }

    /**
     * method for getting page with products table with updated product
     * @param productId ID of product to update
     * @return page with products table with updated product
     */
    @GetMapping("updateProduct/{productId}")
    public ModelAndView updateProduct(@PathVariable("productId")String productId) {
        ModelAndView mv = new ModelAndView("manager/updateProduct");
        mv.addObject("categoryList", categoryService.listCategory());
        mv.addObject("Product", productService.getProductById(Long.parseLong(productId)).get());
        return mv;
    }


}
