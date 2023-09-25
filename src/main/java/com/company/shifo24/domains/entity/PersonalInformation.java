package com.company.shifo24.domains.entity;

import com.company.shifo24.domains.enums.Role;
import com.company.shifo24.domains.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "personal_information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInformation extends BaseEntity{
    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String phoneNumber;

    private String location;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Media media;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}