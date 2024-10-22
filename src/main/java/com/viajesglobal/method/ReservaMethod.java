package com.viajesglobal.method;

import com.viajesglobal.dto.ReservaDTO;

public interface ReservaMethod {
    public String saveReserva(ReservaDTO reservaDTO);
    public String updateReserva(Integer idReserva, ReservaDTO reservaDTO);
}
