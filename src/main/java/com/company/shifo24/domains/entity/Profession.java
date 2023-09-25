package com.company.shifo24.domains.entity;


import lombok.*;
import jakarta.persistence.*;

@Entity(name = "profession")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profession extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String name;
}