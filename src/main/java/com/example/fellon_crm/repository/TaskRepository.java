package com.example.fellon_crm.repository;
import com.example.fellon_crm.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TaskRepository extends JpaRepository<Task, Long> {
}
