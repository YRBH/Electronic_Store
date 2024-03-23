package com.example.electronic.controller;

import com.example.electronic.dto.BuyerDTO;
import com.example.electronic.model.Buyer;
import com.example.electronic.service.BuyerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


@RestController
public class BuyerController {

    @Autowired
    BuyerService buyerService;

    @PostMapping("/buyer")
    public ResponseEntity<String> createBuyer(@Valid @RequestBody BuyerDTO buyerDTO) {
        String name = buyerDTO.getName();
        int age = buyerDTO.getAge();
        Date date = buyerDTO.getDate();

        buyerService.createBuyer(name, age, date);
        return ResponseEntity.ok("Product created successfully");
    }
    @GetMapping("/buyer")
    public List<Buyer> getBuyers(){
        return buyerService.getBuyers();
    }
    @GetMapping("/buyer/{id}")
    public ResponseEntity<Buyer> findProductById(@PathVariable int id){
        Buyer buyer = buyerService.findProductById(id);
        if (buyer != null){
            return new ResponseEntity<>(buyer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/buyer/{id}")
    public ResponseEntity<Void> deleteBuyerById(@PathVariable int id) throws SQLException {
        boolean deleted = buyerService.deleteBuyerById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/buyer/{id}")
    public ResponseEntity<Void> updateBuyerInfo(@PathVariable int id, @RequestBody BuyerDTO buyerDTO) {
        try {
            String name = buyerDTO.getName();
            int age = buyerDTO.getAge();
            java.sql.Date date = buyerDTO.getDate();

            Buyer buyer = new Buyer(id, name, age, date, new Date());
            boolean updated = buyerService.updateBuyerInfo(buyer);
            if (updated) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
