package com.viajesglobal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Vuelos")
public class Vuelo {

    @Id
    @Column(name = "id_vuelo")
    private int idVuelo;

    @Column(name = "id_ruta")
    private int idRuta;

    @Column(name = "numero_vuelo")
    private String numeroVuelo;

    @Column(name = "fecha_salida")
    private LocalDateTime fechaSalida;

    @Column(name = "asientos_totales")
    private int asientosTotales;

    @Column(name = "asientos_disponibles")
    private int asientosDisponibles;
}
