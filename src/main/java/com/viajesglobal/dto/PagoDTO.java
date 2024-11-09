package com.viajesglobal.dto;


import com.viajesglobal.estado.PagoEstado;
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

    private PagoEstado estadoPago;

    private String idTransaccionSecurepay;



}
