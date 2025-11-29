package com.iesvdc.demo.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Provincia {
    @Id
    private Long codigo;

    @Column(nullable = false, length = 50)
    private String nombreProvincia;

    @Column(nullable = false, length = 50)
    private String comunidad;

    @OneToMany(mappedBy = "provincia")
    private List<CodPostal> codPostales;
}
