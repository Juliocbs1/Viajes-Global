package com.viajesglobal.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RutaDTO {
    private int idRuta;

    private int idOrigen;

    private int idDestino;

    private int duracionEstimada;

}
