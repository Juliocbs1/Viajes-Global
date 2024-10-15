package com.viajesglobal.dto;


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
    private ReservaDTO.estado estado;
    private long totalPago;

    private enum estado{
        Pendiente, Confirmada, Cancelada
    }
}
