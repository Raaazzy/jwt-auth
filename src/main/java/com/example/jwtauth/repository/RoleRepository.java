package com.example.jwtauth.repository;


import com.example.jwtauth.models.ERole;
import com.example.jwtauth.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Здесь мы можем стучаться в бд за нашей ролью
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Ищем роль по имени
    Optional<Role> findByName(ERole name);
}
