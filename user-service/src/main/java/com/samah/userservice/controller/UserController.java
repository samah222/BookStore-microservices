package com.samah.userservice.controller;

import com.samah.userservice.dto.UserDto;
import com.samah.userservice.entity.User;
import com.samah.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@Tag(name = "User Controller",
        description = "All CRUD operations for user service and other APIs to be used by other micro-services")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(
            summary = "Add a new user",
            description = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201")
    })
    @PostMapping
    public ResponseEntity<UserDto> addUser(@Validated @RequestBody User user) {
        return new ResponseEntity(userService.addUser(user), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get a user",
            description = "Get a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@Parameter(description = "ID of user to be retrieved",
            required = true) @PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Get all users",
            description = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser() {
        return new ResponseEntity<List<UserDto>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a user",
            description = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@Parameter(description = "ID of user to be updated",
            required = true) @PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Edit a user",
            description = "Edit a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Validated @RequestBody UserDto userDto,
                                              @Parameter(description = "ID of user to be updated",
                                                      required = true) @PathVariable Long id) {
        userService.updateUser(id, userDto);
        return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
    }

}
