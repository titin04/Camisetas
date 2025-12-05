package com.iesvdc.demo.repositorios;

import com.iesvdc.demo.modelos.Camiseta;
import com.iesvdc.demo.modelos.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepoCamiseta extends JpaRepository<Camiseta, Long> {
    List<Camiseta> findByCategoriaId(Long categoriaId);
    Optional<Camiseta> findById(Long id);
}