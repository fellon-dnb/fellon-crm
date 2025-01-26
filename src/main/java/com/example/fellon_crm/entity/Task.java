package com.example.fellon_crm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate deadline;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public enum Status {
        PENDING,
        COMPLETED,
        CANCELLED
        }
}
