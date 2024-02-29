package com.example.todoapp.repository;

import com.example.todoapp.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByNikName(String nikName);
    @Transactional
    void deleteByNikName(String nikName);

}
