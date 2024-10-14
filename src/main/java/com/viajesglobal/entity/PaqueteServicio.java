package com.viajesglobal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Paquete_Servicios")
public class PaqueteServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paquete_servicio")
    private int idPaqueteServicio;
    @Column(name = "id_paquete")
    private int idPaquete;
    @Column(name = "id_servicio")
    private int idServicio;

    private int cantidad;
}
