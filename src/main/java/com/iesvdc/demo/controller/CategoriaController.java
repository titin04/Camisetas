package com.iesvdc.demo.controller;

import java.util.List;
import java.util.Optional;


import com.iesvdc.demo.modelos.Categoria;
import com.iesvdc.demo.repositorios.RepoCategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.NonNull;


@Controller
@RequestMapping("/admin")
public class CategoriaController {

    @Autowired
    RepoCategoria repoCategoria;


    @GetMapping("categoria")
    public String findAll(Model model) {
        model.addAttribute("categorias", repoCategoria.findAll());
        return "categoria/categorias";
    }

    @GetMapping("categoria/hijos/{id}")
    public String findChilds(
            Model model,
            @PathVariable(name = "id") Long id) {

        Optional<Categoria> oCategoria = repoCategoria.findById(id);

        if(oCategoria.isPresent()) {
            Categoria padre = oCategoria.get();
            model.addAttribute("padre", padre);
            model.addAttribute("categorias", repoCategoria.findByPadre(padre));
            return "categoria/categorias-detalle";
        } else {
            model.addAttribute("titulo", "Categoria: ERROR");
            model.addAttribute("mensaje", "No puedo encontrar esa categoría en la base de datos");
            return "categoria/categorias-error";
        }

    }

    @GetMapping("categoria/add")
    public String addForm(Model modelo) {
        modelo.addAttribute("categorias", repoCategoria.findAll());
        modelo.addAttribute("categoria", new Categoria());
        return "categoria/categorias-add";
    }

    @PostMapping("categoria/add")
    public String add(@ModelAttribute("categoria") Categoria categoria) {
        if (categoria.getPadre() != null && categoria.getPadre().getId() != null) {
            Categoria padre = repoCategoria.findById(categoria.getPadre().getId())
                    .orElse(null);
            categoria.setPadre(padre);
        }
        repoCategoria.save(categoria);
        return "redirect:/admin/categoria";
    }

    @GetMapping("categoria/delete/{id}")
    public String deleteForm(
            @PathVariable(name = "id") @NonNull Long id,
            Model modelo) {
        try {
            Optional<Categoria> categoria = repoCategoria.findById(id);
            if (categoria.isPresent()){
                modelo.addAttribute(
                        "categoria", categoria.get());
                return "categoria/categorias-del";
            } else {
                return "categoria/categorias-error";
            }

        } catch (Exception e) {
            return "categoria/categorias-error";
        }
    }

    @PostMapping("categoria/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        Optional<Categoria> padre = repoCategoria.findById(id);
        if (padre.isPresent()) {
            List<Categoria> hijos = repoCategoria.findByPadre(padre.get());
            if (!hijos.isEmpty()) {
                model.addAttribute("titulo", "Error al borrar");
                model.addAttribute("mensaje", "No se puede borrar una categoría que tenga hijos");
                return "categoria/categorias-error";
            }
            repoCategoria.deleteById(id);
        }
        return "redirect:/admin/categoria";
    }

    @GetMapping("categoria/edit/{id}")
    public String editForm(
            @PathVariable @NonNull Long id,
            Model modelo) {

        Optional<Categoria> categoria =
                repoCategoria.findById(id);
        List<Categoria> categorias =
                repoCategoria.findAll();

        if (categoria.isPresent()){
            modelo.addAttribute("categoria", categoria.get());
            modelo.addAttribute("categorias", categorias);
            return "categoria/categorias-edit";
        } else {
            modelo.addAttribute(
                    "mensaje",
                    "Categoria no encontrada");
            modelo.addAttribute(
                    "titulo",
                    "Error en categorías.");
            return "categoria/categorias-error";
        }
    }

    @PostMapping("categoria/edit/{id}")
    public String edit(@ModelAttribute("categoria") Categoria categoria) {
        if (categoria.getPadre() != null && categoria.getPadre().getId() != null) {
            Categoria padre = repoCategoria.findById(categoria.getPadre().getId()).orElse(null);
            categoria.setPadre(padre);
        } else {
            categoria.setPadre(null);
        }
        repoCategoria.save(categoria);
        return "redirect:/admin/categoria";
    }
}
