package com.apisentinel.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String key;       // e.g., require-auth
    private String description;
    private boolean enabled;
    private int penalty;      // How much to deduct from risk score
}
