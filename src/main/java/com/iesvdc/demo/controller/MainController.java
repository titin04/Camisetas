package com.iesvdc.demo.controller;

import com.iesvdc.demo.modelos.Rol;
import com.iesvdc.demo.modelos.Usuario;
import com.iesvdc.demo.repositorios.RepoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class MainController {
    @Autowired
    RepoUsuario repoUsuario;

     @PostMapping("/saluda")
     public String saluda(@ModelAttribute String nombre, Model modelo) {
        modelo.addAttribute("mensaje", "Hola mundo");
        modelo.addAttribute("nombre", nombre);
        return "saluda";
     }

    @GetMapping("/inserta")
    public String getMethodName(Model modelo) {
        Usuario u = new Usuario();
        u.setUsername("pepe");
        u.setNombre("Pepe");
        u.setApellido("Pepe");
        u.setPassword("123456");
        u.setTelefono(634956874);
        u.setEmail("pepe@gmail.com");
        u.setNif("123456F");
        u.setRol(Rol.CLIENTE);

        repoUsuario.save(u);

        modelo.addAttribute("mensaje", "Insertando usuario");
        modelo.addAttribute("nombre", u.getUsername());

        return "saluda";
    }
}
