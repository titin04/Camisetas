package com.iesvdc.demo.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Camiseta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated (EnumType.STRING)
    private Talla talla;

    @Column(nullable = false)
    @Enumerated (EnumType.STRING)
    private Sexo sexo;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private float precio;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}