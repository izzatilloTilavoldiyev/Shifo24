package com.company.shifo24.controller;


import com.company.shifo24.domains.dtos.request.UserCreateDTO;
import com.company.shifo24.domains.dtos.response.UserDTO;
import com.company.shifo24.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @Operation(
            description = "PUT endpoint to update user",
            summary = "update"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{userID}")
    public ResponseEntity<UserDTO> update(
            @PathVariable Long userID,
            @Valid @RequestBody UserDTO userDTO
    ) {
        UserDTO updatedUser = userService.update(userID, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(
            description = "GET endpoint to search user by firsName," +
                    " you can search with part of name and will return List of users",
            summary = "search"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> search(
            @RequestParam String firstName
    ) {
        List<UserDTO> users = userService.searchByName(firstName);
        return ResponseEntity.ok(users);
    }

    @Operation(
            description = "DELETE endpoint to delete user by ID",
            summary = "delete"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userID}")
    public ResponseEntity<String> delete(
            @PathVariable Long userID
    ) {
        userService.delete(userID);
        return ResponseEntity.ok("Successfully deleted!");
    }
}