package com.company.shifo24.repository;

import com.company.shifo24.domains.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query(value = """
           select * from doctor d where lower(d.first_name) like 
           lower(concat('%', :firstName, '%') )
           """, nativeQuery = true)
    List<Doctor> searchByName(String firstName);
}
