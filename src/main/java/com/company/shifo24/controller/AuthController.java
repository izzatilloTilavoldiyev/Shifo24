package com.company.shifo24.controller;


import com.company.shifo24.domains.dtos.request.LoginDTO;
import com.company.shifo24.domains.dtos.response.TokenDTO;
import com.company.shifo24.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(
            description = "POST endpoint to user login",
            summary = "login"
    )
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(
            @RequestBody LoginDTO loginDTO
    ) {
        TokenDTO token = authService.login(loginDTO);
        return ResponseEntity.ok(token);
    }

    @Operation(
            description = "GET endpoint to get new refresh token",
            summary = "get refresh token"
    )
    @GetMapping("/refresh-token")
    public ResponseEntity<TokenDTO> refreshToken(
            @Valid @RequestParam String refreshToken
    ) {
        TokenDTO generatedToken = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(generatedToken);
    }

}