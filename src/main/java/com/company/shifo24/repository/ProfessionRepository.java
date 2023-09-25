package com.company.shifo24.repository;


import com.company.shifo24.domains.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {
    boolean existsByName(String name);
}