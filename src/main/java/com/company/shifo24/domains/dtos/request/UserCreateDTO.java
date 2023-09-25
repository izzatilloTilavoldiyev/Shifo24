package com.company.shifo24.domains.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDTO {
    @NotBlank(message = "FirstName must not be blank")
    private String firstName;

    @NotBlank(message = "Password must not be blank")
    private String password;

    @NotBlank(message = "Confirm password must not be blank")
    private String confirmPassword;
}