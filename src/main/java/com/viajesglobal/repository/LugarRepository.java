package com.viajesglobal.repository;


import com.viajesglobal.entity.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LugarRepository extends JpaRepository<Lugar,Long> {
}
