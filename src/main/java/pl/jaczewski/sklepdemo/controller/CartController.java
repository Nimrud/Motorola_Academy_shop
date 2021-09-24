package pl.jaczewski.sklepdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.jaczewski.sklepdemo.database.BasketDAO;
import pl.jaczewski.sklepdemo.database.ProductDao;

@Controller
public class CartController {

    private ProductDao productDao;
    private BasketDAO basketDAO;

    @Autowired
    public CartController(ProductDao productDao, BasketDAO basketDAO) {
        this.productDao = productDao;
        this.basketDAO = basketDAO;
    }
}
