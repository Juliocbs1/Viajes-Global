package com.viajesglobal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionDTO {
    private int idNotificacion;

    private int idUsuario;

    private NotificacionDTO.tipo tipo;

    private String contenido;

    private NotificacionDTO.estado estado;

    private  enum tipo{
        SMS, Correo, Push
    }

    private enum estado{
        Pendiente, Enviada, Fallida
    }
}
