package com.apisentinel.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class API {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String version;
    private String specificationType; // OpenAPI or Postman
    private String source;            // Internal or External
    private Double riskScore;

    @Lob
    private String rawSpec;
	}

