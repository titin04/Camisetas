package com.iesvdc.demo.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CodPostal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cp;

    @Column(nullable = false, length = 50)
    private String municipio;

    @ManyToOne(optional = false)
    private Provincia provincia;
}
