package com.viajesglobal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicioAdicionalDTO {
    private int idServicio;
    private String descripcion;
    private String nombre;
    private long precio;
}
