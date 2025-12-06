package com.iesvdc.demo.repositorios;

import com.iesvdc.demo.modelos.CodPostal;
import com.iesvdc.demo.modelos.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoCodPostal extends JpaRepository<CodPostal, Long> {

    List<CodPostal> findByMunicipio(String municipio);

    List<CodPostal> findByProvincia(Provincia provincia);
}
