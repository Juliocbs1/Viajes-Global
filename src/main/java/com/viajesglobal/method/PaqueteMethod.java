package com.viajesglobal.method;


import com.viajesglobal.dto.PaqueteDTO;
import java.util.List;


public interface PaqueteMethod {

    public String savePaquete(PaqueteDTO paqueteDTO);
    public String updatePaquete(int idPaquete, PaqueteDTO paqueteDTO);
    public String deletePaquete(int idPaquete);
    public List<PaqueteDTO> getPaquetes();
    public PaqueteDTO getPaquete(int idPaquete);


}
