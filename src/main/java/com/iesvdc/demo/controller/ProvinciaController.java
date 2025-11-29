package com.iesvdc.demo.controller;

import com.iesvdc.demo.modelos.Provincia;
import com.iesvdc.demo.repositorios.RepoProvincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/provincia")
public class ProvinciaController {
    @Autowired
    RepoProvincia repoProvincia;

    @GetMapping("")
    public String findAll(Model modelo) {
        modelo.addAttribute("titulo", "Listado de Provincias");
        modelo.addAttribute("listaProvincias", repoProvincia.findAll());
        return "provincia/list";
    }

    @GetMapping("/add")
    public String addForm(Model modelo) {
        modelo.addAttribute("accion", "añadir");
        modelo.addAttribute("provincia", new Provincia());
        return "provincia/create";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("provincia") Provincia provincia) {
        repoProvincia.save(provincia);
        return "redirect:/admin/provincia";
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model modelo, @PathVariable(name="id") @NonNull Long codigo) {

        Optional<Provincia> oProvincia = repoProvincia.findById(codigo);
        if(oProvincia.isPresent()) {
            modelo.addAttribute("accion", "editar");
            modelo.addAttribute("provincia", oProvincia.get());
            return "provincia/create";
        } else {
            modelo.addAttribute("titulo", "Gestión");
            modelo.addAttribute("mensaje", "Provincia no encontrada");
            return "error";
        }
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute("provincia") Provincia provincia, @PathVariable(name="id") @NonNull Long codigo) {
        repoProvincia.save(provincia);
        return "redirect:/admin/provincia";
    }

    @GetMapping("/delete/{id}")
    public String delForm(Model modelo, @PathVariable(name="id") @NonNull Long codigo) {
        Optional<Provincia> oProvincia = repoProvincia.findById(codigo);
        if(oProvincia.isPresent()) {
            modelo.addAttribute("accion", "borrar");
            modelo.addAttribute("provincia", oProvincia.get());
            return "provincia/create";
        } else {
            modelo.addAttribute("titulo", "Gestión");
            modelo.addAttribute("mensaje", "Provincia no encontrada");
            return "error";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name="id") @NonNull Long codigo, Model modelo) {
        Optional<Provincia> oProvincia = repoProvincia.findById(codigo);
        if (oProvincia.isEmpty()) {
            modelo.addAttribute("titulo", "Gestión");
            modelo.addAttribute("mensaje", "Provincia no encontrada");
            return "error";
        }

        try {
            repoProvincia.deleteById(codigo);
            return "redirect:/admin/provincia";
        } catch (Exception e) {
            modelo.addAttribute("titulo", "Error al borrar");
            modelo.addAttribute("mensaje", "No se pudo borrar la provincia: " + e.getMessage());
            return "error";
        }
    }
}
