package com.iesvdc.demo.repositorios;

import java.util.List;

import com.iesvdc.demo.modelos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RepoCategoria extends JpaRepository<Categoria, Long> {
    //@Query("SELECT c FROM Categoria c WHERE c.padre = :padre")
    List<Categoria> findByPadre(Categoria padre);
}