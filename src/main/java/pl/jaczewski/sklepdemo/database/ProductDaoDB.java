package pl.jaczewski.sklepdemo.database;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.jaczewski.sklepdemo.model.Product;

import java.util.List;

@Repository
public interface ProductDaoDB extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Product findProductById(@Param("id") Long id);

    List<Product> findAllProducts();
}
