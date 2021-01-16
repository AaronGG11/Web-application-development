package com.aagg.wad.repository;

import com.aagg.wad.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM product p, user u, user_product up " +
            "WHERE up.user_user_id = u.user_id " +
            "AND up.product_product_id = p.product_id " +
            "AND up.user_user_id = :id", nativeQuery = true)
    List<Product> getProductsByUser(@Param("id") Integer id);

}
