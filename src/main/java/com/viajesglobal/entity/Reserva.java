package com.viajesglobal.entity;

import com.viajesglobal.estado.ReservaEstado;
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
    @Enumerated(EnumType.STRING)
    private ReservaEstado estado;

    @Column(name = "total_pago")
    private long totalPago;

    public Reserva(int idUsuario, int idPaquete, ReservaEstado estado, long totalPago) {
        this.idUsuario = idUsuario;
        this.idPaquete = idPaquete;
        this.estado = estado;
        this.totalPago = totalPago;
    }

}
