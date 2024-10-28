package com.viajesglobal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LugarDTO {
    private int idDestino;
    private String nombre;
    private String pais;
    private  String ciudad;

}
