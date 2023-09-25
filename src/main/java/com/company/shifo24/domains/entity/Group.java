package com.company.shifo24.domains.entity;


import lombok.*;
import jakarta.persistence.*;

@Entity(name = "group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Group extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String name;
}