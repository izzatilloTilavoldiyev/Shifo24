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
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;

    @OneToOne(cascade = CascadeType.ALL)
    private Media media;

    @ManyToOne
    @JoinColumn(name = "workplace_id")
    private Workplace workplace;

    @Column(nullable = false)
    private Double meetingCost;

}
