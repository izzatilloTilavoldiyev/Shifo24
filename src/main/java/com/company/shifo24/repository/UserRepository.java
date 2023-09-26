package com.company.shifo24.repository;

import com.company.shifo24.domains.entity.User;
import com.company.shifo24.domains.entity.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    @Query(value = """
           from users u 
           where lower(u.firstName) like 
           lower(concat('%', :firstName, '%') )
           """)
    List<User> searchByName(String firstName);
}
