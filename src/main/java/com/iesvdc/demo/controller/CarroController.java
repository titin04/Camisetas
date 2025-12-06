package com.iesvdc.demo.controller;

import com.iesvdc.demo.modelos.*;
import com.iesvdc.demo.repositorios.RepoCamiseta;
import com.iesvdc.demo.repositorios.RepoLineaPedido;
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

import java.time.LocalDate;
import java.util.List;

@Controller
public class CarroController {
    @Autowired
    private RepoCamiseta repoCamiseta;

    @Autowired
    private RepoPedido repoPedido;

    @Autowired
    RepoUsuario repoUsuario;

    @Autowired
    RepoLineaPedido repoLineaPedido;

    private Usuario getLoggedUser() {
        // Del contexto de la aplicación obtenemos el usuario
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        // obtenemos el usuario del repositorio por su "username"
        return repoUsuario.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));

    }

    @GetMapping("/carro")
    public String findCarro(Model model) {
        List<LineaPedido> lineaPedidos = null;

        Usuario cliente = getLoggedUser();

        long total = 0;

        // Para el usuario que hizo login, buscamos un pedido (sólo puede haber uno) en estado "CARRITO"
        List<Pedido> pedidos = repoPedido.findByEstadoAndCliente(Estado.CARRITO, cliente);
        if (pedidos.size()>0) {
            lineaPedidos = repoLineaPedido.findByPedido(pedidos.get(0));
            for (LineaPedido lp : pedidos.get(0).getLineaPedidos()) {
                total += lp.getCantidad()*lp.getCamiseta().getPrecio();
            }
        }

        // mandamos a la vista los modelos: Pedido y su lista de LineaPedido
        model.addAttribute("lineapedidos", lineaPedidos);
        model.addAttribute("total", total);

        // modelo.addAttribute("productos", productos );
        return "carro/list";
    }


    @GetMapping("/carro/add/{id}")
    public String addToCarro(@PathVariable Long id) {
        Usuario cliente = getLoggedUser();

        // Buscar camiseta
        Camiseta camiseta = repoCamiseta.findById(id)
                .orElseThrow(() -> new RuntimeException("Camiseta no encontrada"));

        // Buscar pedido en estado CARRITO del cliente
        Pedido pedido = repoPedido.findByEstadoAndCliente(Estado.CARRITO, cliente)
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    Pedido nuevo = new Pedido();
                    nuevo.setCliente(cliente);
                    nuevo.setEstado(Estado.CARRITO);
                    nuevo.setFecha(LocalDate.now());
                    return repoPedido.save(nuevo);
                });

        // Crear línea de pedido
        LineaPedido lp = new LineaPedido();
        lp.setPedido(pedido);
        lp.setCamiseta(camiseta);
        lp.setCantidad(1); // por defecto 1, luego podrás editar
        lp.setPrecio(camiseta.getPrecio()); // congelar precio actual

        repoLineaPedido.save(lp);

        // Redirigir al carro
        return "redirect:/carro";
    }

    @GetMapping("/carro/edit/{id}")
    public String editLineaPedido(@PathVariable Long id, Model model) {
        LineaPedido lp = repoLineaPedido.findById(id)
                .orElseThrow(() -> new RuntimeException("Línea de pedido no encontrada"));

        model.addAttribute("lineapedido", lp);
        return "carro/edit";
    }

    @PostMapping("/carro/edit/{id}")
    public String updateLineaPedido(@PathVariable Long id,
                                    @RequestParam("cantidad") Integer cantidad) {
        LineaPedido lp = repoLineaPedido.findById(id)
                .orElseThrow(() -> new RuntimeException("Línea de pedido no encontrada"));

        lp.setCantidad(cantidad);
        repoLineaPedido.save(lp);

        return "redirect:/carro";
    }

    @GetMapping("/carro/del/{id}")
    public String deleteLineaPedido(@PathVariable Long id) {
        LineaPedido lp = repoLineaPedido.findById(id)
                .orElseThrow(() -> new RuntimeException("Línea de pedido no encontrada"));

        repoLineaPedido.delete(lp);

        return "redirect:/carro";
    }

    @GetMapping("/carro/confirmar")
    public String confirmarCarro(Model model) {
        Usuario cliente = getLoggedUser();

        Pedido pedido = repoPedido.findByEstadoAndCliente(Estado.CARRITO, cliente)
                .stream()
                .findFirst()
                .orElse(null);

        if (pedido == null) {
            return "redirect:/camiseta";
        }

        double total = 0;
        for (LineaPedido lp : pedido.getLineaPedidos()) {
            total += lp.getCantidad() * lp.getCamiseta().getPrecio();
        }

        model.addAttribute("pedido", pedido);
        model.addAttribute("lineapedidos", pedido.getLineaPedidos());
        model.addAttribute("total", total);

        return "carro/confirm"; // tu confirm.html
    }

    @PostMapping("/carro/confirmar")
    public String terminarCarro() {
        Usuario cliente = getLoggedUser();

        Pedido pedido = repoPedido.findByEstadoAndCliente(Estado.CARRITO, cliente)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay carro para confirmar"));

        pedido.setEstado(Estado.REALIZADO);
        pedido.setFecha(LocalDate.now());
        repoPedido.save(pedido);

        return "redirect:/pedidos";
    }

}
