package com.viajesglobal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Lugares")
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lugar")
    private int idLugar;

    private String nombre;
    private String pais;
    private  String ciudad;

    public Lugar( String nombre, String pais, String ciudad) {
        this.nombre=nombre;
        this.pais=pais;
        this.ciudad=ciudad;
    }

}
