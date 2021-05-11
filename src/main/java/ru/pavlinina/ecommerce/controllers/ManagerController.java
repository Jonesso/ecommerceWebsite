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

    @GetMapping("index")
    public String index() {
        return "manager/index";
    }

    //	Category--------------------------------------------------
    @GetMapping("category-form")
    public ModelAndView listCategory() {
        ModelAndView mv = new ModelAndView("manager/category-form");
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }

    @PostMapping("add-category")
    public ModelAndView addCategory(Category category) {
        ModelAndView mv = new ModelAndView("manager/category-form");
        categoryService.addCategory(category);
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }

    @GetMapping("delete-Category/{categoryId}")
    public ModelAndView deleteCategory(@PathVariable("categoryId")String categoryId) {
        ModelAndView mv = new ModelAndView("manager/category-form");
        categoryService.deleteCategory(Long.parseLong(categoryId));
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }

    @GetMapping("updateCategory/{categoryId}")
    public ModelAndView updateCategory(@PathVariable("categoryId")String categoryId) {
        ModelAndView mv = new ModelAndView("manager/updateCategory");
        mv.addObject("Category", categoryService.getCategory(Long.parseLong(categoryId)).get());
        return mv;
    }


    //	Product--------------------------------------------------
    @GetMapping("product-form")
    public ModelAndView listProduct() {
        ModelAndView mv = new ModelAndView("manager/product-form");
        mv.addObject("categoryList", categoryService.listCategory());
        mv.addObject("productList", productService.listProduct());
        return mv;
    }

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

}
