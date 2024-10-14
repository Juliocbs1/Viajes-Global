package com.viajesglobal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Reservas")
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private int idPago;
    @Column(name = "id_reserva")
    private int idReserva;

    private long monto;
    @Column(name = "metodo_pago")
    private metodoPago metodoPago;
    @Column(name = "estado_pago")
    private estadoPago estadoPago;
    @Column(name = "id_transaccion_securepay")
    private String idTransaccionSecurepay;

    private enum estadoPago{
        Pendiente, Completado, Fallido
    }
    private enum metodoPago{
        Tarjeta,Transferencia,Paypal
    }


}
