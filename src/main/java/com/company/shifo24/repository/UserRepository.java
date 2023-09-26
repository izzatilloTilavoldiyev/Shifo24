package com.company.shifo24.repository;

import com.company.shifo24.domains.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);

    @Query(value = """
           select * from users u where lower(u.first_name) like 
           lower(concat('%', :firstName, '%') )
           """, nativeQuery = true)
    List<User> searchByName(String firstName);
}
