package com.viajesglobal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notificaciones")
public class Notificacion {
    @Id
    @Column(name = "id_notificacion")
    private int idNotificacion;
    @Column(name = "id_usuario")
    private int idUsuario;

    private tipo tipo;

    private String contenido;

    private estado estado;

    private  enum tipo{
      SMS, Correo, Push
    }

    private enum estado{
        Pendiente, Enviada, Fallida
    }

}
