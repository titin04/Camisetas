package com.iesvdc.demo.controller;

import com.iesvdc.demo.modelos.Estado;
import com.iesvdc.demo.modelos.Pedido;
import com.iesvdc.demo.repositorios.RepoPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/pedido")
public class GestionPedidoController {

    @Autowired
    private RepoPedido repoPedido;

    @GetMapping("")
    public String listarTodos(Model model) {
        List<Pedido> pedidos = repoPedido.findAll();
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("estados", Estado.values());
        return "gestionpedido/list";
    }

    @PostMapping("/updateEstado/{id}")
    public String updateEstado(@PathVariable Long id,
                               @RequestParam("estado") Estado estado) {
        Optional<Pedido> oPedido = repoPedido.findById(id);
        Pedido pedido;
        if (oPedido.isPresent()) {
            pedido = oPedido.get();
        } else {
            throw new RuntimeException("Pedido no encontrado");
        }
        pedido.setEstado(estado);
        repoPedido.save(pedido);
        return "redirect:/admin/pedido";
    }
}

