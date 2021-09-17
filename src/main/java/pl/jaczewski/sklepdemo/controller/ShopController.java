package pl.jaczewski.sklepdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jaczewski.sklepdemo.database.UserDAO;
import pl.jaczewski.sklepdemo.model.Product;
import pl.jaczewski.sklepdemo.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ShopController {

    private final ProductService productService;
    private final UserDAO userDAO;

    @Autowired
    public ShopController(ProductService productService, UserDAO userDAO) {
        this.productService = productService;
        this.userDAO = userDAO;
    }

    @GetMapping("/allProducts")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getData());
        return "listProducts";
    }

    @GetMapping("/allProducts18")
    public String listProducts18(Model model) {
        model.addAttribute("products", productService.getDataForEveryone());
        return "listProducts";
    }
    @GetMapping("/admin/allProducts")
    public String adminListProducts(Model model) {
        model.addAttribute("products", productService.getData());
        return "adminListProducts";
    }

    @GetMapping("/allProducts/{name}")
    public String displayProduct(Model model, @PathVariable String name) {
        model.addAttribute("product", productService.getProduct(name));
        return "productDetails";
    }

    @GetMapping("/allCustomers")
    public String displayCustomers(Model model) {
        model.addAttribute("customers", userDAO.allCustomers());
        return "listCustomers";
    }

    @GetMapping("/allUsers/{id}")
    public String displayUserDetails(Model model, @PathVariable Long id) {
        model.addAttribute("user", userDAO.userById(id));
        return "userDetails";
    }

    @GetMapping("/admin/addProduct")
    public String addProduct(@RequestParam(required = false, defaultValue = "-1") int id, Model model) {
        Product product = productService.getProduct(id);
        if (product == null) {
            product = new Product("", "", new BigDecimal(0.00), Product.Category.NONE, 0);
        }
        List<Product.Category> categories = productService.getCategories();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "newProduct";
    }

    @PostMapping("/admin/addProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {
        if (product.getId() == 0) {
            productService.addProduct(product);
        } else {
            productService.updateProduct(product);
        }
        return "redirect:/admin/allProducts";
    }

    @GetMapping("/admin/deleteProduct")
    public String deleteProduct(@RequestParam int id) {
        productService.removeProduct(id);
        return "redirect:/admin/allProducts";
    }
}
