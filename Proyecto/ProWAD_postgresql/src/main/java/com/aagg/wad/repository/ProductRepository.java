package com.aagg.wad.repository;

import com.aagg.wad.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM product p, person u, person_product up " +
            "WHERE up.person_person_id = u.person_id " +
            "AND up.product_product_id = p.product_id " +
            "AND up.person_person_id = :id", nativeQuery = true)
    List<Product> getProductsByPerson(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO person_product VALUES" +
            "(:id_person, :id_product)", nativeQuery = true)
    void savePersonProduct(@Param("id_product") Integer id_product, @Param("id_person") Integer id_person);

    @Query(value = "SELECT * FROM product " +
            "WHERE availability = true " +
            "ORDER BY product_id ASC", nativeQuery = true)
    List<Product> getAvailableProducts();


    @Transactional
    @Modifying
    @Query(value = "UPDATE product SET stock = stock -1 " +
            "WHERE product_id = :id", nativeQuery = true)
    void decreaseStock(@Param("id") Integer product_id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM person_product " +
            "WHERE person_person_id = :person_id " +
            "AND product_product_id = :product_id", nativeQuery = true)
    void deleteProductPersonBy(@Param("person_id") Integer person_id, @Param("product_id") Integer product_id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO product_address " +
            "values(:product_id, :state_state_id, :town_town_id)",
            nativeQuery = true)
    void saveProductAddress(@Param("product_id") Integer product_id, @Param("state_state_id") Integer state_id, @Param("town_town_id") Integer town_id);


    @Query(value = "SELECT * FROM product_address " +
            "WHERE product_id = :product_id",
            nativeQuery = true)
    List findProductAddressById(@Param("product_id") Integer product_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE product set " +
            "availability = :availability, " +
            "cost = :cost, " +
            "description = :description, " +
            "name = :name, " +
            "stock = :stock " +
            "WHERE product_id = :product_id", nativeQuery = true)
    void update(@Param("product_id") Integer product_id,
                   @Param("availability") Boolean availability,
                   @Param("cost") Float cost,
                   @Param("description") String description,
                   @Param("name") String name,
                   @Param("stock") Integer stock);

    @Query(value = "SELECT COUNT (*) " +
            "FROM person p, person_role pr, role r " +
            "WHERE p.person_id = pr.person_id " +
            "AND pr.role_id = r.role_id " +
            "AND r.role_id = :role_id", nativeQuery = true)
    Integer countUserTypeByRoleId(@Param("role_id") Integer role_id);

    @Query(value = "SELECT COUNT (*) " +
            "FROM person p", nativeQuery = true)
    Integer countAllUsers();




}
