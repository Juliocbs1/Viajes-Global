package com.viajesglobal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaqueteDTO {
    private int idPaquete;
    private String nombre;
    private String descripcion;
    private long precio;
    private int disponibilidad;
    private int idDestino;

}
