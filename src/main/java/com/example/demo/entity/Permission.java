package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Permission implements GrantedAuthority {

    @NonNull
    private String code;

    @Override
    public String getAuthority() {
        return code;
    }
}
