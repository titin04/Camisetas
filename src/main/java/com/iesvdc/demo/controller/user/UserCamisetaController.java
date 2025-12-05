package com.iesvdc.demo.controller.user;

import com.iesvdc.demo.repositorios.RepoCamiseta;
import com.iesvdc.demo.repositorios.RepoCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/camiseta")
public class UserCamisetaController {

    @Autowired
    RepoCamiseta repoCamiseta;

    @Autowired
    RepoCategoria repoCategoria;

    @GetMapping("")
    public String camiseta(@RequestParam(name="categoriaId", required=false) Long categoriaId, Model model) {
        model.addAttribute("titulo", "Listado de Camisetas");
        model.addAttribute("categorias", repoCategoria.findAll());

        if (categoriaId != null) {
            model.addAttribute("listaCamisetas", repoCamiseta.findByCategoriaId(categoriaId));
            model.addAttribute("categoriaSeleccionada", categoriaId);
        } else {
            model.addAttribute("listaCamisetas", repoCamiseta.findAll());
        }

        return "camiseta/user/list";
    }


}
