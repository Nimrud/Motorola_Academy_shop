package pl.jaczewski.sklepdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jaczewski.sklepdemo.repository.UserDao;
import pl.jaczewski.sklepdemo.model.ItemInBasket;
import pl.jaczewski.sklepdemo.model.Product;
import pl.jaczewski.sklepdemo.service.database.BasketServiceDbImpl;
import pl.jaczewski.sklepdemo.service.database.ProductServiceDb;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ShopController {

    private final BasketServiceDbImpl basketServiceDb;
    private final ProductServiceDb productServiceDb;
    private final UserDao userDAO;

    @Autowired
    public ShopController(BasketServiceDbImpl basketServiceDb, ProductServiceDb productServiceDb, UserDao userDAO) {
        this.basketServiceDb = basketServiceDb;
        this.productServiceDb = productServiceDb;
        this.userDAO = userDAO;
    }

    @GetMapping("/allProducts")
    public String listProducts(Model model) {
        model.addAttribute("products", productServiceDb.findAll());
        model.addAttribute("item", new ItemInBasket());
        return "listProducts";
    }

//    @GetMapping("/allProductsUnder18yo")
//    public String listProducts18(Model model) {
//        model.addAttribute("products", productService.getProductsForEveryone());
//        return "listProducts";
//    }

    @GetMapping("/admin/allProducts")
    public String adminListProducts(Model model) {
        model.addAttribute("products", productServiceDb.findAll());
        return "adminListProducts";
    }

    @GetMapping("/allProducts/{name}")
    public String displayProduct(Model model, @PathVariable String name) {
        model.addAttribute("product", productServiceDb.getProductByName(name));
        return "productDetails";
    }

    @PostMapping("/order/{name}")
    public String addProductToBasket(@PathVariable String name, @ModelAttribute("itemToBasket") ItemInBasket itemToBasket,
                                     Model model) {
        if (basketServiceDb.getItemByName(name) == null) {
            itemToBasket.setProduct(productServiceDb.getProductByName(name));
            basketServiceDb.addItem(itemToBasket);
        } else {
            basketServiceDb.getItemByName(name).setQuantityInBasket(itemToBasket.getQuantityInBasket());
        }
        model.addAttribute("basket", basketServiceDb);
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
        Product product = productServiceDb.getProductById(id);
        if (product == null) {
            product = new Product("", "", new BigDecimal(0.00), Product.Category.NONE, 0);
        }
        List<Product.Category> categories = productServiceDb.getAllCategories();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "newProduct";
    }

    @PostMapping("/admin/addProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {
        if (product.getId() == 0) {
            productServiceDb.create(product);
        } else {
            productServiceDb.update(product);
        }
        return "redirect:/admin/allProducts";
    }

    @GetMapping("/admin/deleteProduct")
    public String deleteProduct(@RequestParam int id) {
        productServiceDb.delete(id);
        return "redirect:/admin/allProducts";
    }

    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/about")
    public String aboutUs() {
        return "about";
    }

    @GetMapping("/basket")
    public String basket(Model model) {
        model.addAttribute("basket", basketServiceDb);
        return "basket";
    }

    @GetMapping("/basket/removeItem/{name}")
    public String removeItemFromBasket(@PathVariable String name) {
        ItemInBasket itemInBasket = basketServiceDb.getItemByName(name);
        basketServiceDb.removeItem(itemInBasket);
        return "redirect:/basket";
    }

    @PostMapping("/finalise")
    @ResponseBody
    public String finalisePurchase() {
        // TODO
        return "Zamówienie zostało złożone!";
    }
}
