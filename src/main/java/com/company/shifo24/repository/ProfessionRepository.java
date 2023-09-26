package com.company.shifo24.repository;


import com.company.shifo24.domains.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {
    boolean existsByName(String name);

    @Query(value = """
           select * from profession p where lower(p.name) like 
           lower(concat('%', :name, '%') )
           """, nativeQuery = true)
    List<Profession> searchByName(String name);
}