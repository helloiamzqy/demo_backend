package com.example.demo.dto;

import com.example.demo.entity.Permission;
import com.example.demo.entity.SysUser;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserAuthDTO {

    private String id;
    private String username;
    private Set<String> permissions = new HashSet<>();

    public UserAuthDTO(SysUser user) {
        id = user.getId();
        username = user.getUsername();
        permissions.addAll(user.getPermissions().stream().map(Permission::getAuthority).collect(Collectors.toSet()));
    }
}
