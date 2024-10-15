package com.viajesglobal.dto;


import com.viajesglobal.estado.ReservaEstado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
    private int idReserva;
    private int idUsuario;
    private int idPaquete;
    private ReservaEstado estado;
    private long totalPago;


}
