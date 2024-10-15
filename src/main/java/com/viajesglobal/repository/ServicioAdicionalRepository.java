package com.viajesglobal.repository;

import com.viajesglobal.entity.ServicioAdicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioAdicionalRepository extends JpaRepository<ServicioAdicional, Long> {
}
