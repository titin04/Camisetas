package com.iesvdc.demo.controller;

import com.iesvdc.demo.modelos.Rol;
import com.iesvdc.demo.modelos.Usuario;
import com.iesvdc.demo.repositorios.RepoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    RepoUsuario repoUsuario;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            Model model,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String email,
            @RequestParam int telefono,
            @RequestParam String nif,
            @RequestParam String rol) {

        try {
            Usuario usuario = new Usuario();
            usuario.setUsername(username);
            usuario.setPassword(passwordEncoder.encode(password)); // encriptar
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            usuario.setTelefono(telefono);
            usuario.setNif(nif);
            usuario.setEnabled(true);
            usuario.setRol(Rol.valueOf(rol));

            repoUsuario.save(usuario);

            model.addAttribute("mensaje", "Usuario creado correctamente");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al crear usuario: " + e.getMessage());
            return "register";
        }
    }


    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("titulo", "ERROR");
        model.addAttribute("mensaje", "Error genérico");
        return "error";
    }
}


