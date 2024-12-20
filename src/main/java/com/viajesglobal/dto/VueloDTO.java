package com.viajesglobal.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VueloDTO {

    private int idVuelo;

    private int idRuta;

    private String numeroVuelo;

    private LocalDateTime fechaSalida;

    private int asientosTotales;

    private int asientosDisponibles;

    private long costoAsiento;

    public VueloDTO(int idRuta,String numeroVuelo,LocalDateTime fechaSalida,int asientosTotales,int asientosDisponibles,long costoAsiento) {
        this.idRuta = idRuta;
        this.numeroVuelo = numeroVuelo;
        this.fechaSalida = fechaSalida;
        this.asientosTotales = asientosTotales;
        this.asientosDisponibles = asientosDisponibles;
        this.costoAsiento = costoAsiento;

    }
}
