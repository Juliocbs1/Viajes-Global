package com.viajesglobal.dto;


import com.viajesglobal.estado.NotificacionEstado;
import com.viajesglobal.estado.TipoNotificacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionDTO {
    private int idNotificacion;

    private int idUsuario;

    private TipoNotificacion tipo;

    private String contenido;

    private NotificacionEstado estado;


}
