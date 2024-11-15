package com.viajesglobal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaqueteServicioDTO {

    private int idPaqueteServicio;
    private int idPaquete;
    private int idServicio;
    private int cantidad;
}
