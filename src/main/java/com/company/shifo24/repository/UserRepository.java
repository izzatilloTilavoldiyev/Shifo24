package com.company.shifo24.repository;

import com.company.shifo24.domains.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
