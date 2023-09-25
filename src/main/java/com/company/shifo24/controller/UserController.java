package com.company.shifo24.controller;


import com.company.shifo24.domains.dtos.request.UserCreateDTO;
import com.company.shifo24.domains.dtos.response.UserDTO;
import com.company.shifo24.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Operation(
            description = "POST endpoint to create user",
            summary = "create user"
    )
    @PostMapping
    public ResponseEntity<UserDTO> create(
            @Valid @RequestBody UserCreateDTO userCreateDTO
    ) {
        UserDTO userDTO = userService.create(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @Operation(
            description = "GET endpoint to get user by ID",
            summary = "get by ID"
    )
    @GetMapping("/{userID}")
    public ResponseEntity<UserDTO> getByID(
            @PathVariable Long userID
    ) {
        UserDTO user = userService.getByID(userID);
        return ResponseEntity.ok(user);
    }

    @Operation(
            description = "GET endpoint to get all users",
            summary = "get all"
    )
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @Operation(
            description = "PUT endpoint to update user",
            summary = "update"
    )
    @PutMapping("/{userID}")
    public ResponseEntity<UserDTO> update(
            @PathVariable Long userID,
            @Valid @RequestBody UserDTO userDTO
    ) {
        UserDTO updatedUser = userService.update(userID, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(
            description = "DELETE endpoint to delete user by ID",
            summary = "delete"
    )
    @DeleteMapping("/{userID}")
    public ResponseEntity<String> delete(
            @PathVariable Long userID
    ) {
        userService.delete(userID);
        return ResponseEntity.ok("Successfully deleted!");
    }
}