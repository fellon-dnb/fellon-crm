package com.example.fellon_crm.repository;
import com.example.fellon_crm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
