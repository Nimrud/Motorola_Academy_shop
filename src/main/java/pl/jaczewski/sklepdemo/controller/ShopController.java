package pl.jaczewski.sklepdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.jaczewski.sklepdemo.database.UserDAO;
import pl.jaczewski.sklepdemo.service.ProductService;

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
}
