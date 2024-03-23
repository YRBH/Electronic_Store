package com.example.electronic.service;


import com.example.electronic.model.Product;
import com.example.electronic.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public boolean createProduct(String name, int count, double price, int grade) {
        if (grade < 0 || grade > 5) {
            return false;
        }

        Product product = new Product(name, count, price, grade);
        return productRepository.createProduct(product);

    }

    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    public Product findProductById(int id) {
        return productRepository.findProductById(id);
    }

    public boolean deleteProductById(int id) {
        return productRepository.deleteProductById(id);
    }

    public boolean updateProductInfo(int id, String name, int count, double price, int grade) {
        Product product = new Product(id, name, count, price, grade, new Date());
        productRepository.updateProductInfo(product);
        return true;
    }
}
