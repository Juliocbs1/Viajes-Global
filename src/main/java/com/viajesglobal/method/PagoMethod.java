package com.viajesglobal.method;

import com.viajesglobal.dto.PagoDTO;

import java.util.List;

public interface PagoMethod {

    public String savePago(PagoDTO pagoDTO);
    public List<PagoDTO> getPagos();
}
