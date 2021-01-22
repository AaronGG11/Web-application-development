package com.aagg.wad.service;

import com.aagg.wad.model.Product;
import com.aagg.wad.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts()
    {
        return productRepository.findAll();
    }

    public Product findProductById(Integer Id)
    {
        return productRepository.findById(Id).orElse(null);
    }

    public Product saveProduct(Product product)
    {
        return productRepository.save(product);
    }

    public List<Product> getProductsByPerson(Integer id)
    {
        return productRepository.getProductsByPerson(id);
    }

    public void savePersonProduct(Integer id_product, Integer id_person)
    {
        productRepository.savePersonProduct(id_product, id_person);
    }

    public List<Product> getAvailableProducts()
    {
        return productRepository.getAvailableProducts();
    }

    public void decreaseStock(Integer id)
    {
        productRepository.decreaseStock(id);
    }

    public void deleteProductById(Integer id)
    {
        productRepository.deleteById(id);
    }

    public void deleteProductPersonBy(Integer person_id, Integer product_id)
    {
        productRepository.deleteProductPersonBy(person_id, product_id);
    }

    public void saveProductAddress(Integer product_id, Integer state_id, Integer town_id)
    {
        productRepository.saveProductAddress(product_id, state_id, town_id);
    }

    public List findProductAddressById(Integer product_id)
    {
        return productRepository.findProductAddressById(product_id);
    }

    public void updateProduct(Product product)
    {
        productRepository.update(product.getId(),
                product.getAvailability(),
                product.getCost(),
                product.getDescription(),
                product.getName(),
                product.getStock());
    }

    public Integer countUserTypeByRoleId(Integer role_id)
    {
        return productRepository.countUserTypeByRoleId(role_id);
    }

    public Integer countAllUsers()
    {
        return productRepository.countAllUsers();
    }
}
