package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
public class AuditEntity {

    @CreatedBy
    protected String createUser;
    @CreatedDate
    protected LocalDateTime createTime;
    @LastModifiedBy
    protected String updateUser;
    @LastModifiedDate
    protected LocalDateTime updateTime;
}
