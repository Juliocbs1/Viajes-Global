package com.viajesglobal.repository;

import com.viajesglobal.entity.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Integer> {
    List<Ruta> findByIdOrigenAndIdDestino(Integer idOrigen, Integer idDestino);
}
