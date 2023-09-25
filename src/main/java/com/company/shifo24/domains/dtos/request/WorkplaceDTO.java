package com.company.shifo24.domains.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkplaceDTO {
    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Location must not be blank")
    private String location;
}