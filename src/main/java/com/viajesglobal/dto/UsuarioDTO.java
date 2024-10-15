package com.viajesglobal.dto;

import com.viajesglobal.entity.Usuario;
import jakarta.persistence.Column;
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

    private UsuarioDTO.notificacion preferenciaNotificacion;


    private enum notificacion{
        SMS,Correo,Push
    }
}
