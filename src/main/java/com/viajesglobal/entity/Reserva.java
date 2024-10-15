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
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private int idReserva;
    @Column(name = "id_usuario")
    private int idUsuario;
    @Column(name = "id_paquete")
    private int idPaquete;

    private estado estado;

    @Column(name = "total_pago")
    private long totalPago;

    private enum estado{
        Pendiente, Confirmada, Cancelada
    }
}
