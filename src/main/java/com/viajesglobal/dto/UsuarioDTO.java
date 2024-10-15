package com.viajesglobal.dto;


import com.viajesglobal.estado.NotificacionEstado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private long idUsuario;

    private String nombre;
    private String correo;
    private String telefono;
    private String contrasena;

    private NotificacionEstado preferenciaNotificacion;

}
