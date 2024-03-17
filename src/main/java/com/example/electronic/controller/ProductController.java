package com.example.electronic.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProductController {

    @PostMapping("/product")
    private boolean createProduct(@RequestBody Map<String, Object> map) {
        String name = (String) map.get("name");
        Object countO = map.get("count");
        Object priceO = map.get("price");
        Object gradeO = map.get("grade");

        Integer count = objectToInteger(countO);
        Double price = objectToDouble(priceO);
        Integer grade = objectToInteger(gradeO);


        if (name.isEmpty() || count == null || price == null){
            return false;
        }

        System.out.println(name);
        System.out.println(count);
        System.out.println(price);
        System.out.println(grade);

        return true;
    }

    private Double objectToDouble (Object object){
        Double aDouble = null;
        if (object instanceof Double) {
            aDouble = (Double) object;
        } else if (object instanceof Integer) {
            aDouble = ((Integer) object).doubleValue();
        } else if (object instanceof String) {
            aDouble = Double.parseDouble((String) object);
        }
        return aDouble;
    }

    private Integer objectToInteger(Object object){
        Integer anInteger = null;
        if (object instanceof Integer){
            anInteger = (Integer) object;
        } else if (object instanceof Double) {
            anInteger = ((Double) object).intValue();
        } else if (object instanceof String) {
            anInteger = Integer.parseInt((String) object);
        }
        return anInteger;
    }
}
