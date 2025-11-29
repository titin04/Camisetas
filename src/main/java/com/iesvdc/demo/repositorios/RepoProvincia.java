package com.iesvdc.demo.repositorios;

import com.iesvdc.demo.modelos.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepoProvincia extends JpaRepository<Provincia, Long> {
    List<Provincia> findByComunidad(String comunidad);
    Optional<Provincia> findByCodigo(Long codigo);
}
