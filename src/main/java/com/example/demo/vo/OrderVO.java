package com.example.demo.vo;

import lombok.Data;

@Data
public class OrderVO {

    private String id;
    private String userId;
    private String merchandiseId;
    private String merchandiseName;
    private Integer quantity;
}
