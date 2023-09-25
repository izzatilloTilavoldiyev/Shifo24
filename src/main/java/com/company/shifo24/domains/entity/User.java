package com.company.shifo24.domains.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends PersonalInformation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_information_id")
    private PersonalInformation personalInformation;
}