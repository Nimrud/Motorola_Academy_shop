package pl.jaczewski.sklepdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.jaczewski.sklepdemo.service.BasketService;
import pl.jaczewski.sklepdemo.service.ProductService;

@Controller
public class BasketController {

    private ProductService productService;
    private BasketService basketService;

    @Autowired
    public BasketController(ProductService productService, BasketService basketService) {
        this.productService = productService;
        this.basketService = basketService;
    }
}
