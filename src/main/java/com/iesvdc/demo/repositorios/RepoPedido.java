package com.iesvdc.demo.repositorios;

import com.iesvdc.demo.modelos.Estado;
import com.iesvdc.demo.modelos.Pedido;
import com.iesvdc.demo.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepoPedido extends JpaRepository<Pedido, Long> {

    List<Pedido> findByEstado(Estado estado);

    @Query("SELECT pedido FROM Pedido pedido  WHERE pedido.estado != ?1")
    List<Pedido> findDistinctEstado(Estado estado);

    List<Pedido> findByEstadoAndCliente(Estado estado, Usuario cliente);
    List<Pedido> findByCliente(Usuario cliente);
    List<Pedido> findByOperario(Usuario operario);
    List<Pedido> findByEstadoAndOperario(Estado estado, Usuario operario);

}
