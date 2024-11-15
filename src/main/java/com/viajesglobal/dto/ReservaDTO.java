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

    private Integer idPaquete;
    private ReservaEstado estado;
    private long totalPago;

    private int idVuelo;

    private int cantidadAiento;

    public ReservaDTO(int idUsuario,ReservaEstado estado,long totalPago,int idVuelo,int cantidadAiento) {
        this.idUsuario = idUsuario;
        this.estado = estado;
        this.totalPago = totalPago;
        this.idVuelo = idVuelo;
        this.cantidadAiento = cantidadAiento;

    }




}
