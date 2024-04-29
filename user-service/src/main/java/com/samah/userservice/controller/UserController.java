package com.samah.userservice.controller;

import com.samah.userservice.dto.UserDto;
import com.samah.userservice.dto.UserRegistrationDto;
import com.samah.userservice.event.RegistrationCompleteEvent;
import com.samah.userservice.event.SendMailEvent;
import com.samah.userservice.mail.MailSenderService;
import com.samah.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
    @Autowired
    private ApplicationEventPublisher registrationPublisher;

    @Operation(summary = "Register a new user", description = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201")})
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Validated @RequestBody UserRegistrationDto registrationDto,
                                                HttpServletRequest request) {
        UserDto dto = userService.addUser(registrationDto);
        //registrationPublisher.publishEvent(new RegistrationCompleteEvent(dto, applicationUrl(request)));
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }

    @Operation(summary = "Verify token", description = "Verify the token for a new registered user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201")})
    @GetMapping("/verifyRegistration")
    public ResponseEntity<String> verifyUserRegistration(@RequestParam("token") String token) {
        int result = userService.validateVerificationToken(token);
        if (result == 1)
            return new ResponseEntity<>("User validated", HttpStatus.OK);
        else if (result == 0)
            return new ResponseEntity<>("User token is expired", HttpStatus.OK);
        else
            return new ResponseEntity<>("Token is not valid", HttpStatus.OK);
    }

    @Operation(summary = "Send a new token", description = "Send a new token for a new registered user, due to delay or error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201")})
    @GetMapping("/resendVerificationToken")
    public ResponseEntity<String> resendVerificationToken(@RequestParam("email") String email) {
        String result = userService.resendVerificationToken(email);
        if (result == null)
            return new ResponseEntity<>("email not found, please register now", HttpStatus.OK);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //
    @Operation(summary = "Verify new token", description = "Verify a new token after resend request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201")})
    @GetMapping("/newToken")
    public ResponseEntity<String> verifyNewToken(@RequestParam("token") String token) {
        int result = userService.validateVerificationToken(token);
        if (result == 1)
            return new ResponseEntity<>("User validated", HttpStatus.OK);
        else if (result == 0)
            return new ResponseEntity<>("User token is expired", HttpStatus.OK);
        else
            return new ResponseEntity<>("Token is not valid", HttpStatus.OK);
    }

    //change password
    //reset password

    @Operation(summary = "Get a user", description = "Get a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@Parameter(description = "ID of user to be retrieved",
            required = true) @PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get all users", description = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser() {
        return new ResponseEntity<List<UserDto>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Operation(summary = "Delete a user", description = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@Parameter(description = "ID of user to be updated",
            required = true) @PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Edit a user", description = "Edit a user")
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

    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
