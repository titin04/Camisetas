package com.iesvdc.demo.controller;

import com.iesvdc.demo.modelos.Camiseta;
import com.iesvdc.demo.modelos.Talla;
import com.iesvdc.demo.modelos.Sexo;
import com.iesvdc.demo.repositorios.RepoCamiseta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/camiseta")
public class CamisetaController {

    @Autowired
    RepoCamiseta repoCamiseta;

    @GetMapping("")
    public String camiseta(Model model) {
        model.addAttribute("titulo", "Listado de Camisetas");
        model.addAttribute("listaCamisetas", repoCamiseta.findAll());
        return "camiseta/list"; // listado
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("camiseta", new Camiseta());
        model.addAttribute("Talla", Talla.values());
        model.addAttribute("Sexo", Sexo.values());
        return "camiseta/create"; // formulario añadir
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("camiseta") Camiseta camiseta) {
        repoCamiseta.save(camiseta);
        return "redirect:/admin/camiseta";
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable(name="id") @NonNull Long id) {
        Optional<Camiseta> oCamiseta = repoCamiseta.findById(id);
        if (oCamiseta.isPresent()) {
            model.addAttribute("camiseta", oCamiseta.get());
            model.addAttribute("Talla", Talla.values());
            model.addAttribute("Sexo", Sexo.values());
            return "camiseta/edit"; // formulario editar
        } else {
            model.addAttribute("titulo", "Gestión");
            model.addAttribute("mensaje", "Camiseta no encontrada");
            return "error";
        }
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute("camiseta") Camiseta camiseta, @PathVariable(name="id") @NonNull Long id) {
        repoCamiseta.save(camiseta);
        return "redirect:/admin/camiseta";
    }

    @GetMapping("/delete/{id}")
    public String delForm(Model model, @PathVariable(name="id") @NonNull Long id) {
        Optional<Camiseta> camiseta = repoCamiseta.findById(id);
        if (camiseta.isPresent()) {
            model.addAttribute("camiseta", camiseta.get());
            return "camiseta/delete"; // formulario borrar
        } else {
            model.addAttribute("titulo", "Gestión");
            model.addAttribute("mensaje", "Camiseta no encontrada");
            return "error";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name="id") @NonNull Long id, Model model) {
        Optional<Camiseta> oCamiseta = repoCamiseta.findById(id);
        if (oCamiseta.isEmpty()) {
            model.addAttribute("titulo", "Gestión");
            model.addAttribute("mensaje", "Camiseta no encontrada");
            return "error";
        }

        try {
            repoCamiseta.deleteById(id);
            return "redirect:/admin/camiseta";
        } catch (Exception e) {
            model.addAttribute("titulo", "Error al borrar");
            model.addAttribute("mensaje", "No se pudo borrar la camiseta: " + e.getMessage());
            return "error";
        }
    }
}
