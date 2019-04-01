package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {

    @NotBlank
    private String userKey;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
