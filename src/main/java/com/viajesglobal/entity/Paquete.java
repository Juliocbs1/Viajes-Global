package com.viajesglobal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Paquetes")
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paquete")
    private int idPaquete;

    private String nombre;
    private String descripcion;
    private long precio;
    private int disponibilidad;
    @Column(name = "id_ruta")
    private int idRuta;



}
