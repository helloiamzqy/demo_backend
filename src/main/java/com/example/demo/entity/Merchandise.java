package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document
public class Merchandise extends AuditEntity {

    @Id
    @Field("_id")
    private String id;

    private String name;

    private String type;

    private Double price;

    private Integer inventory;
}
