package com.viajesglobal.method;

import com.viajesglobal.dto.VueloDTO;

import java.util.List;

public interface VueloMethod {

    public String insertarVuelo(VueloDTO vuelo);
    public VueloDTO getVuelo(Integer id);
    public String borrarVuelo(Integer id);
    public String modificarVuelo(Integer id, VueloDTO vuelo);
    public List<VueloDTO> listarVuelos();

}
