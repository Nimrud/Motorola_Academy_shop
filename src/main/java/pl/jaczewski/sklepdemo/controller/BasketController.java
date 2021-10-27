package pl.jaczewski.sklepdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.jaczewski.sklepdemo.service.database.BasketServiceDbImpl;
import pl.jaczewski.sklepdemo.service.database.ProductServiceDb;

@Controller
public class BasketController {

    private ProductServiceDb productServiceDb;
    private BasketServiceDbImpl basketServiceDb;

    @Autowired
    public BasketController(ProductServiceDb productServiceDb, BasketServiceDbImpl basketServiceDb) {
        this.productServiceDb = productServiceDb;
        this.basketServiceDb = basketServiceDb;
    }
}
