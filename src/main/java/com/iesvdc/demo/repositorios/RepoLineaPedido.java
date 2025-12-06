package com.iesvdc.demo.repositorios;

import com.iesvdc.demo.modelos.LineaPedido;
import com.iesvdc.demo.modelos.Pedido;
import com.iesvdc.demo.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoLineaPedido extends JpaRepository<LineaPedido, Long> {

    List<LineaPedido> findByPedido(Pedido pedido);


    @Query("SELECT lp " +
            "FROM LineaPedido lp " +
            "JOIN Pedido pedido on lp.pedido = pedido " +
            "WHERE lp = :lineaPedido " +
            "AND pedido.cliente = :usuario")
    List<LineaPedido> lineaPedidoBelongsToUser(LineaPedido lineaPedido, Usuario usuario);

}
