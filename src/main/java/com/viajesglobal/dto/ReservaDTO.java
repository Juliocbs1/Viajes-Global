package com.viajesglobal.dto;


import com.viajesglobal.estado.ReservaEstado;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {

    private int idUsuario;
    private int idPaquete;
    private ReservaEstado estado;
    private long totalPago;
    private int idRuta;

    private Date fechaInicio;

    private Date fechaFin;

    private int idVuelo;
    
    private int cantidadAiento;


    public ReservaDTO(int idUsuario, int idPaquete,int idRuta ,ReservaEstado estado, long totalPago,Date fechaInicio, Date fechaFin,int idVuelo,int cantidadAiento) {
        this.idUsuario = idUsuario;
        this.idPaquete = idPaquete;
        this.estado = estado;
        this.totalPago = totalPago;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idVuelo = idVuelo;
        this.cantidadAiento = cantidadAiento;
        this.idRuta = idRuta;
    }

}
