package com.viajesglobal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "preferencia_notificacion")
    private notificacion preferenciaNotificacion;


    private enum notificacion{
        SMS,Correo,Push
    }
}
