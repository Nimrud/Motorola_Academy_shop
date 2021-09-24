package pl.jaczewski.sklepdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jaczewski.sklepdemo.database.UserDAO;
import pl.jaczewski.sklepdemo.model.ItemInBasket;
import pl.jaczewski.sklepdemo.model.Product;
import pl.jaczewski.sklepdemo.service.BasketService;
import pl.jaczewski.sklepdemo.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ShopController {

    private final BasketService basketService;
    private final ProductService productService;
    private final UserDAO userDAO;

    @Autowired
    public ShopController(BasketService basketService, ProductService productService, UserDAO userDAO) {
        this.basketService = basketService;
        this.productService = productService;
        this.userDAO = userDAO;
    }

    @GetMapping("/allProducts")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("item", new ItemInBasket());
        return "listProducts";
    }

    @GetMapping("/allProductsUnder18yo")
    public String listProducts18(Model model) {
        model.addAttribute("products", productService.getProductsForEveryone());
        return "listProducts";
    }
    @GetMapping("/admin/allProducts")
    public String adminListProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "adminListProducts";
    }

    @GetMapping("/allProducts/{name}")
    public String displayProduct(Model model, @PathVariable String name) {
        model.addAttribute("product", productService.getProduct(name));
        return "productDetails";
    }

    @PostMapping("/order/{name}")
    public String addProductToBasket(@PathVariable String name, @ModelAttribute("itemToBasket") ItemInBasket itemToBasket,
                                     Model model) {
        if (basketService.getItemByName(name) == null) {
            itemToBasket.setProduct(productService.getProduct(name));
            basketService.addItem(itemToBasket);
        } else {
            basketService.getItemByName(name).setQuantityInBasket(itemToBasket.getQuantityInBasket());
        }
        model.addAttribute("basket", basketService);
        model.addAttribute("itemToBasket", itemToBasket);
        return "redirect:/allProducts";
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

    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/basket")
    public String basket(Model model) {
        model.addAttribute("basket", basketService);
        return "basket";
    }

    @GetMapping("/basket/removeItem/{name}")
    public String removeItemFromBasket(@PathVariable String name) {
        ItemInBasket itemInBasket = basketService.getItemByName(name);
        basketService.removeItem(itemInBasket);
        return "redirect:/basket";
    }

    @PostMapping("/finalise")
    @ResponseBody
    public String finalisePurchase() {
        // TODO
        return "Zamówienie zostało złożone!";
    }
}
