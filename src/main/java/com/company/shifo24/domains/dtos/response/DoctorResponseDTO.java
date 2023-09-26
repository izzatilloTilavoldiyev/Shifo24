package com.company.shifo24.domains.dtos.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponseDTO {
    private String firstName;

    private String lastName;

    private Long professionID;

    private Long mediaID;

    private Long workplaceID;

    private Double meetingCost;
}