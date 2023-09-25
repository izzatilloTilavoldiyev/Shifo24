package com.company.shifo24.domains.entity;


import lombok.*;
import jakarta.persistence.*;

@Entity(name = "workplace")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Workplace extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;
}