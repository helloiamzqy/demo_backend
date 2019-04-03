package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrderCreateDTO {

    @NotBlank
    private String merchandiseId;
    @NotNull
    @Min(0)
    private Integer quantity;
}
