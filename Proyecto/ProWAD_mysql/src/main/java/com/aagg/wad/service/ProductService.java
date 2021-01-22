package com.aagg.wad.service;

import com.aagg.wad.model.Product;
import com.aagg.wad.repository.ProductRepository;
import com.aagg.wad.repository.StateRepository;
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

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Product findProductById(Integer Id){
        return productRepository.findById(Id).orElse(null);
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getProductsByUser(Integer id)
    {
        return productRepository.getProductsByUser(id);
    }

    public void saveUserProduct(Integer id_product, Integer id_user){
        productRepository.saveUserProduct(id_product, id_user);
    }

    public List<Product> getAvailableProducts(){
        return productRepository.getAvailableProducts();
    }

    public void decreaseStock(Integer id){
        productRepository.decreaseStock(id);
    }
}
