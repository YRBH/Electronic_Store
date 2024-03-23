package com.example.electronic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class BuyerDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Count cannot be null")
    private Integer age;

    @NotNull(message = "Price cannot be null")
    private Date date;

}
