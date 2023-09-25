package com.company.shifo24.domains.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity(name = "doctor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor extends PersonalInformation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String workExperience;
    private Double meetingCost;

    @ManyToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;

    @ManyToOne
    @JoinColumn(name = "workplace_id")
    private Workplace workplace;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_information_id")
    private PersonalInformation personalInformation;
}