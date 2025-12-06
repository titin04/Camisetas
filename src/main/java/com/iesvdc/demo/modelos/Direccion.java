package com.iesvdc.demo.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String tipoVia;

    @Column(nullable = false, length = 100)
    private String nombreVia;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String planta;

    @Column(nullable = false)
    private String puerta;

    @Column(nullable = false)
    private String portal;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne(optional = false)
    private CodPostal codigoPostal;

    @ManyToOne(optional = false)
    private Usuario usuario;
}
