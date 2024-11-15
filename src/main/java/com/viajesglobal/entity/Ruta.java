package com.viajesglobal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Rutas")
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta")
    private int idRuta;
    @Column(name = "id_origen")
    private int idOrigen;
    @Column(name = "id_destino")
    private int idDestino;

    @Column(name = "duracion_estimada")
    private int duracionEstimada;

}
