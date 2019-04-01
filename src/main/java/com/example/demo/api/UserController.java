package com.example.demo.api;

import com.example.demo.core.ErrorResponse;
import com.example.demo.core.ErrorResponses;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponses.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
})
@Api(description = "UserResource")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get all users")
    @ApiResponse(code = 404, message = "SysUser not exits.", response = ErrorResponse.class)
    @GetMapping("")
    public List<UserDTO> list() {
        return userService.list();
    }

    @ApiOperation("Get user by id")
    @ApiImplicitParam(name = "id", value = "SysUser Id")
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") String id) {
        return userService.findUserById(id);
    }

    @ApiOperation(value = "Update user info", notes = "Success return user id.")
    @ApiImplicitParam(name = "dto", value = "SysUser Info", dataType = "UserDTO")
    @PostMapping("")
    public String updateUser(@RequestBody @Validated UserDTO dto) {
        return userService.update(dto);
    }
}
