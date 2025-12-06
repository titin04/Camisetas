package com.iesvdc.demo.controller;

import com.iesvdc.demo.modelos.Pedido;
import com.iesvdc.demo.modelos.Usuario;
import com.iesvdc.demo.repositorios.RepoPedido;
import com.iesvdc.demo.repositorios.RepoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PedidoController {

    @Autowired
    private RepoPedido repoPedido;

    @Autowired
    private RepoUsuario repoUsuario;

    private Usuario getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return repoUsuario.findByUsername(username)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
    }

    @GetMapping("/pedidos")
    public String listarPedidos(Model model) {
        Usuario cliente = getLoggedUser();
        List<Pedido> pedidos = repoPedido.findByCliente(cliente);
        model.addAttribute("pedidos", pedidos);
        return "pedido/list";
    }

    @GetMapping("/pedido/edit/{id}")
    public String editPedido(@PathVariable Long id, Model model) {
        Pedido pedido = repoPedido.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        model.addAttribute("pedido", pedido);
        return "pedido/edit";
    }

    @PostMapping("/pedido/edit/{id}")
    public String updatePedido(@PathVariable Long id,
                               @RequestParam("observaciones") String observaciones) {
        Pedido pedido = repoPedido.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedido.setObservaciones(observaciones);
        repoPedido.save(pedido);

        return "redirect:/pedidos";
    }

}
