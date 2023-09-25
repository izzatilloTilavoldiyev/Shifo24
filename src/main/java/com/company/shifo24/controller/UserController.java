package com.company.shifo24.controller;


import com.company.shifo24.domains.dtos.request.UserCreateDTO;
import com.company.shifo24.domains.dtos.response.UserResponseDTO;
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
    public ResponseEntity<UserResponseDTO> create(
            @Valid @RequestBody UserCreateDTO userCreateDTO
    ) {
        UserResponseDTO userResponseDTO = userService.create(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @Operation(
            description = "GET endpoint to get user by ID",
            summary = "get by ID"
    )
    @GetMapping("/{userID}")
    public ResponseEntity<UserResponseDTO> getByID(
            @PathVariable Long userID
    ) {
        UserResponseDTO user = userService.getByID(userID);
        return ResponseEntity.ok(user);
    }

    @Operation(
            description = "GET endpoint to get all users",
            summary = "get all"
    )
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<UserResponseDTO> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
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