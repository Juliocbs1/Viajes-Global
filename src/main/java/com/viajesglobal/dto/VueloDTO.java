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


    private int idRuta;

    private String numeroVuelo;

    private LocalDateTime fechaSalida;

    private int asientosTotales;

    private int asientosDisponibles;

    private long costoAsiento;
}
