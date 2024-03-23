package com.example.electronic.controller;

import com.example.electronic.dto.ProductDTO;
import com.example.electronic.model.Product;
import com.example.electronic.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        String name = productDTO.getName();
        int count = productDTO.getCount();
        double price = productDTO.getPrice();
        int grade = productDTO.getGrade();

        productService.createProduct(name,count,price,grade);
        return ResponseEntity.ok("Product created successfully");
    }

    @GetMapping("/product")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product = productService.findProductById(id);
        if (product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable int id) throws SQLException {
        boolean deleted = productService.deleteProductById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Void> updateProductInfo(@PathVariable int id, @RequestBody ProductDTO productDTO) throws SQLException {
        String name = productDTO.getName();
        int count = productDTO.getCount();
        double price = productDTO.getPrice();
        int grade = productDTO.getGrade();

        boolean updated = productService.updateProductInfo(id,name,count,price,grade);
        if (updated){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
