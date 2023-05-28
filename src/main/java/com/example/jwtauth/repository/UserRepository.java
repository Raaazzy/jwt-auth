package com.example.jwtauth.repository;

import com.example.jwtauth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Здесь мы можем стучаться в бд за нашим пользователем
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Ищем пользователя по имени
    Optional<User> findByUsername(String username);

    // Проверяем существует ли пользователей по заданному имени
    Boolean existsByUsername(String username);

    // Проверяем существует ли пользователей по заданной почте
    Boolean existsByEmail(String Email);
}
