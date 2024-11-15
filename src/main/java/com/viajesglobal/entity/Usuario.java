package com.viajesglobal.entity;

import com.viajesglobal.estado.TipoNotificacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @Column(name = "id_usuario")
    private long idUsuario;

    private String nombre;
    private String correo;
    private String telefono;
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(name = "preferencia_notificacion")
    private TipoNotificacion preferenciaNotificacion;


}
