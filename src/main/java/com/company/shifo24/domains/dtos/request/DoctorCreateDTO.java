package com.company.shifo24.domains.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorCreateDTO {
    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @NotNull(message = "Profession must not be null")
    private Long professionID;

    @NotNull(message = "Media must not be null")
    private Long mediaID;

    @NotNull(message = "Workplace must not be null")
    private Long workplaceID;

    @NotNull(message = "Meeting cost must not be blank")
    private Double meetingCost;
}