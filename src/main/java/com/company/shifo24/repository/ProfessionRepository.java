package com.company.shifo24.repository;


import com.company.shifo24.domains.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {

    // Class methods go here
}