package com.viajesglobal.repository;

import com.viajesglobal.entity.PaqueteServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaqueteServicioRepository extends JpaRepository<PaqueteServicio, Long> {
}
