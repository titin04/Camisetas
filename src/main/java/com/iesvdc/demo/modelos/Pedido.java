package com.iesvdc.demo.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String observaciones;
    private Float descuento;
    private Float total;

    @ManyToOne
    private Usuario cliente;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToMany(mappedBy = "pedido")
    private List<LineaPedido> lineaPedidos;

    @ManyToOne
    private Usuario operario;

    @ManyToOne
    private Direccion direccion;
}
