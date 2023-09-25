package com.company.shifo24.domains.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity(name = "doctor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor extends BaseEntity{
    private String firstName;
    private String lastName;
}
