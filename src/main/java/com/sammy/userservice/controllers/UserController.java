package com.sammy.userservice.controllers;

import com.sammy.userservice.dtos.requests.UpdateUserRequest;
import com.sammy.userservice.dtos.responses.ApiResponse;
import com.sammy.userservice.dtos.responses.UserResponse;
import com.sammy.userservice.exceptions.UserNotFoundException;
import com.sammy.userservice.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable String id){
        try {
            UserResponse response = userService.getUserById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse(true,"user found",response));
        }catch (UserNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false,e.getMessage(),null));
        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse> getUserByEmail(@PathVariable String email){
        try {
            UserResponse response = userService.getUserByEmail(email);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse(true,"user found",response));
        }catch (UserNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false,e.getMessage(),null));
        }
    }
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<ApiResponse> getUserByPhone(@PathVariable String phoneNumber){
        try {
            UserResponse response = userService.getUserByPhoneNumber(phoneNumber);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse(true,"user found",response));
        }catch (UserNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false,e.getMessage(),null));
        }
    }
    @GetMapping
    public ResponseEntity<ApiResponse> getUsers(){
        Page<UserResponse> allUsers = userService.getAllUsers();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(true,"users found",allUsers));
    }
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchUsers(@RequestParam String query){
        Page<UserResponse> users = userService.searchUser(query);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(true,"Search results fetched successfully",users));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable String id, @Valid @RequestBody UpdateUserRequest request){
        try {
            UserResponse response = userService.updateUser(id, request);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse(true,"user updated",response));
        }catch (UserNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false,e.getMessage(),null));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String id){
        try {
            userService.deleteUserById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse(true, "User deleted successfully", null));
        }catch (UserNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false,e.getMessage(),null));
        }
    }
    @PatchMapping("deactivate/{id}")
    public ResponseEntity<ApiResponse> deactivateUser(@PathVariable String id){
        try {
            UserResponse response = userService.deactivateUserById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse(true,"user deactivated successfully",response));
        }catch (UserNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false,e.getMessage(),null));
        }
    }
    @PatchMapping("activate/{id}")
    public ResponseEntity<ApiResponse> activateUser(@PathVariable String id){
        try {
            UserResponse response = userService.activateUserById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse(true,"user activated successfully",response));
        }catch (UserNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false,e.getMessage(),null));
        }
    }
}
