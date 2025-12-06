package com.iesvdc.demo.controller;

import com.iesvdc.demo.modelos.CodPostal;
import com.iesvdc.demo.modelos.Usuario;
import com.iesvdc.demo.modelos.Direccion;
import com.iesvdc.demo.repositorios.RepoCodPostal;
import com.iesvdc.demo.repositorios.RepoUsuario;
import com.iesvdc.demo.repositorios.RepoDireccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private RepoUsuario repoUsuario;

    @Autowired
    private RepoDireccion repoDireccion;

    @Autowired
    private RepoCodPostal repoCodPostal;

    private Usuario getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return repoUsuario.findByUsername(username).orElse(null);
    }

    @GetMapping
    public String perfilUsuario(Model model) {
        Usuario usuario = getLoggedUser();
        List<Direccion> direcciones = usuario.getDirecciones();
        List<CodPostal> codPostales = repoCodPostal.findAll();

        model.addAttribute("usuario", usuario);
        model.addAttribute("direcciones", direcciones);
        model.addAttribute("newDireccion", new Direccion());
        model.addAttribute("codPostales", codPostales);

        return "usuario/usuario";
    }


    @GetMapping("/edit")
    public String editarUsuario(Model model) {
        Usuario usuario = getLoggedUser();
        model.addAttribute("usuario", usuario);
        return "usuario/edit";
    }

    @PostMapping("/edit")
    public String actualizarUsuario(@ModelAttribute Usuario usuarioForm) {
        Usuario usuario = getLoggedUser();
        if (usuario != null) {
            usuario.setNombre(usuarioForm.getNombre());
            usuario.setApellido(usuarioForm.getApellido());
            usuario.setEmail(usuarioForm.getEmail());
            usuario.setTelefono(usuarioForm.getTelefono());
            usuario.setNif(usuarioForm.getNif());
            repoUsuario.save(usuario);
        }
        return "redirect:/usuario";
    }

    @GetMapping("/direcciones/add")
    public String mostrarFormularioDireccion(Model model) {
        model.addAttribute("newDireccion", new Direccion());
        model.addAttribute("codPostales", repoCodPostal.findAll());
        return "usuario/add"; // aquí va tu add.html
    }

    @PostMapping("/direcciones/add")
    public String addDireccion(@ModelAttribute Direccion direccion) {
        Usuario usuario = getLoggedUser();
        direccion.setUsuario(usuario);
        repoDireccion.save(direccion);
        return "redirect:/usuario";
    }


    @GetMapping("/direcciones/delete/{id}")
    public String deleteDireccion(@PathVariable Long id) {
        repoDireccion.deleteById(id);
        return "redirect:/usuario";
    }
}
