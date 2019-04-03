package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document
public class Order extends AuditEntity {

    @Id
    @Field("_id")
    private String id;

    @Indexed
    private String userId;

    @DBRef
    private Merchandise merchandise;

    private String merchandiseName;

    private Double price;

    private Integer quantity;
}
