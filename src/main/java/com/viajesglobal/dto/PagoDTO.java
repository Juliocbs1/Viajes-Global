package com.viajesglobal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoDTO {

    private int idPago;

    private int idReserva;

    private long monto;

    private PagoDTO.metodoPago metodoPago;

    private PagoDTO.estadoPago estadoPago;

    private String idTransaccionSecurepay;

    private enum estadoPago{
        Pendiente, Completado, Fallido
    }
    private enum metodoPago{
        Tarjeta,Transferencia,Paypal
    }

}
