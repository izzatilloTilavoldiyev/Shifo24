package com.company.shifo24.domains.entity;

import com.company.shifo24.domains.enums.Role;
import lombok.*;
import jakarta.persistence.*;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity{

    @Column(nullable = false)
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    private Media media;

    @Column(unique = true)
    private String email;

}