package com.viajesglobal.entity;

import com.viajesglobal.estado.ReservaEstado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

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
    @Column(name = "id_ruta")
    private int idRuta;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Enumerated(EnumType.STRING)
    private ReservaEstado estado;

    @Column(name = "id_vuelo")
    private int idVuelo;

    @Column(name = "cantidad_asientos")
    private int cantidadAiento;

    @Column(name = "total_pago")
    private long totalPago;

    public Reserva(int idUsuario, int idPaquete,int idRuta ,ReservaEstado estado, long totalPago,Date fechaInicio, Date fechaFin,int idVuelo,int cantidadAiento) {
        this.idUsuario = idUsuario;
        this.idPaquete = idPaquete;
        this.estado = estado;
        this.totalPago = totalPago;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idVuelo = idVuelo;
        this.cantidadAiento = cantidadAiento;
        this.idRuta = idRuta;
    }


}
