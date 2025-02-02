package com.example.fellon_crm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Status status;

    public String getStatusDisplayName() {
        if (Status.ACTIVE.equals(this.status)) {
            return "Активный";
        } else if (Status.INACTIVE.equals(this.status)) {
            return "Неактивный";
        }
        return this.status.name();  // на случай, если статус не был установлен
    }

    public enum Status {
        ACTIVE,
        INACTIVE
    }
}
