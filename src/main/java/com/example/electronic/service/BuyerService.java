package com.example.electronic.service;

import com.example.electronic.model.Buyer;
import com.example.electronic.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BuyerService {
    @Autowired
    BuyerRepository buyerRepository;

    public boolean createBuyer(String name, int age, Date date){
        Buyer buyer = new Buyer(name,age,date);
        return buyerRepository.createBuyer(buyer);
    }

    public List<Buyer> getBuyers(){
        return buyerRepository.getBuyers();
    }

    public Buyer findProductById(int id){
        return buyerRepository.findProductById(id);
    }
    public boolean deleteBuyerById(int id){
        return buyerRepository.deleteBuyerById(id);
    }

    public boolean updateBuyerInfo(Buyer buyer) {
        return buyerRepository.updateBuyerInfo(buyer);
    }
}
