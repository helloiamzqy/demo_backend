package com.example.demo.dto;

import lombok.Data;

@Data
public class MerchandiseDTO {

    private String id;

    private String name;

    private String type;

    private Double price;

    private Integer inventory;
}
