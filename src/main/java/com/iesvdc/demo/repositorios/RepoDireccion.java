package com.iesvdc.demo.repositorios;

import com.iesvdc.demo.modelos.Direccion;
import com.iesvdc.demo.modelos.Usuario;
import com.iesvdc.demo.modelos.CodPostal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoDireccion extends JpaRepository<Direccion, Long> {

    List<Direccion> findByUsuario(Usuario usuario);

    List<Direccion> findByCodigoPostal(CodPostal codigoPostal);

    List<Direccion> findByNombreVia(String nombreVia);
}
