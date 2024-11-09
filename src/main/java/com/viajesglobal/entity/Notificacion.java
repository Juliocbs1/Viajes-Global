package com.viajesglobal.entity;

import com.viajesglobal.estado.NotificacionEstado;
import com.viajesglobal.estado.TipoNotificacion;
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

    @Enumerated(EnumType.STRING)
    private TipoNotificacion tipo;

    private String contenido;

    private NotificacionEstado estado;



}
