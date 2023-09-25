package com.company.shifo24.domains.dtos.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private Long mediaID;
}