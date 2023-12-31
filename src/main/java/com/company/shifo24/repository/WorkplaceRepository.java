package com.company.shifo24.repository;


import com.company.shifo24.domains.entity.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {
    boolean existsByName(String name);

    @Query(value = """
           select * from workplace w where lower(w.name) 
           like lower(concat('%', :name, '%') )
           """, nativeQuery = true)
    List<Workplace> searchByName(String name);
}