package com.viajesglobal.method;

import com.viajesglobal.dto.RutaDTO;

import java.util.List;

public interface RutaMethod {
    public String insertarRuta(RutaDTO ruta);
    public String borrarRuta(Integer id);
    public String modificarRuta(Integer id, RutaDTO ruta);
    public List<RutaDTO> listarRuta();
    public RutaDTO getRuta(Integer id);

    List<RutaDTO> findByIdOrigenAndIdDestino(Integer idOrigen, Integer idDestino);
}
