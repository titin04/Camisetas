package com.iesvdc.demo.modelos;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(unique = true, nullable = false, length = 25)
    private String username;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @Column(unique = true, nullable = false, length = 120)
    private String email;

    @Column(nullable = false)
    private int telefono;

    @Column(nullable = false, length = 10)
    private String nif;

    @OneToMany(mappedBy = "usuario")
    private List<Direccion> direcciones;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;
}