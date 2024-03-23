package com.example.electronic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Count cannot be null")
    private Integer count;

    @NotNull(message = "Price cannot be null")
    private Double price;

    @NotNull(message = "Grade cannot be null")
    private Integer grade;

}