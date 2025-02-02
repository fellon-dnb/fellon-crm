package com.example.fellon_crm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Task {
    private String title;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status; // Правильно используете перечисление для статуса задачи

    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "customer_id") // Это правильно для связи с Customer
    private Customer customer;

    // Перечисление для статуса задачи
    public enum Status {
        PENDING,   // Ожидает выполнения
        COMPLETED, // Завершена
        CANCELLED  // Отменена
    }
}
