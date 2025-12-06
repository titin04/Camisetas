package com.iesvdc.demo.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LineaPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float precio;
    private Integer cantidad;

    @ManyToOne
    private Camiseta camiseta;

    @ManyToOne
    private Pedido pedido;
}
