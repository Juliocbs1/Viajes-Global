package com.viajesglobal.entity;

import com.viajesglobal.estado.PagoEstado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Reservas")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private int idPago;
    @Column(name = "id_reserva")
    private int idReserva;

    private long monto;
    @Column(name = "estado_pago")
    private PagoEstado estadoPago;
    @Column(name = "id_transaccion_securepay")
    private String idTransaccionSecurepay;





}
