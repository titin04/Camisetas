package com.iesvdc.demo.controller.user;

import com.iesvdc.demo.repositorios.RepoProvincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/provincia")
public class UserProvinciaController {
    @Autowired
    RepoProvincia repoProvincia;

    @GetMapping("")
    public String findAll(Model modelo) {
        modelo.addAttribute("titulo", "Listado de Provincias");
        modelo.addAttribute("listaProvincias", repoProvincia.findAll());
        return "provincia/user/list";
    }
}
