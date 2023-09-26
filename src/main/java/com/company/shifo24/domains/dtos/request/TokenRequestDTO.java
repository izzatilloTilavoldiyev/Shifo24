package com.company.shifo24.domains.dtos.request;


import jakarta.validation.constraints.NotBlank;

public record TokenRequestDTO(
        @NotBlank String email,
        @NotBlank String password
) {
}