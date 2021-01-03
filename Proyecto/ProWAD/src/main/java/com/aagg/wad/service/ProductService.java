package com.aagg.wad.service;

import com.aagg.wad.model.Product;
import com.aagg.wad.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
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
}
