//package pl.jaczewski.sklepdemo.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//import pl.jaczewski.sklepdemo.model.ItemInBasket;
//import pl.jaczewski.sklepdemo.model.Product;
//
//import java.math.BigDecimal;
//
//@Component
//public class StringToItemConverter implements Converter<String, ItemInBasket> {
//
//    @Autowired
//    private ItemInBasket itemInBasket;
//
//    @Override
//    public ItemInBasket convert(String source) {
//        String[] input = source.split(",");
//        return new ItemInBasket(
//                new Product(
//                  input[0],
//                  input[1],
//                  new BigDecimal(input[2]),
//                  Product.Category.valueOf(input[3]),
//                  Integer.parseInt(input[4])),
//                Integer.parseInt(input[5])
//        );
//    }
//}
