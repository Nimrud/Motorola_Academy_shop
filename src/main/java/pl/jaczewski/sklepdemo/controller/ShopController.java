package pl.jaczewski.sklepdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jaczewski.sklepdemo.product.ProductDao;

@Controller
public class ShopController {

    private final ProductDao productDao;

    @Autowired
    public ShopController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/all")
    public String listProducts(Model model) {
        model.addAttribute("products", productDao.all());
        return "listProducts";
    }
}
