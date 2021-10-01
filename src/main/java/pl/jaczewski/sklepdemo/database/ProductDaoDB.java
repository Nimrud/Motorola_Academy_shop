package pl.jaczewski.sklepdemo.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.jaczewski.sklepdemo.model.Product;

@Repository
public interface ProductDaoDB extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Product findProductById(@Param("id") Long id);

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Product findProductByName(@Param("name") String name);
}
