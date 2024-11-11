package com.viajesglobal.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "costo_asiento")
    private long costoAsiento;

    public Vuelo(Integer idRuta,String numeroVuelo, LocalDateTime fechaSalida, int asientosTotales, int asientosDisponibles,long costoAsiento) {
        this.idRuta = idRuta;
        this.numeroVuelo = numeroVuelo;
        this.fechaSalida = fechaSalida;
        this.asientosTotales = asientosTotales;
        this.asientosDisponibles = asientosDisponibles;
        this.costoAsiento = costoAsiento;
    }
}
