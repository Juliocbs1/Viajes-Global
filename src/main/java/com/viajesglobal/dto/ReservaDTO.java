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


    private Date fechaInicio;

    private Date fechaFin;

    private int idVuelo;
    
    private int cantidadAiento;




}
