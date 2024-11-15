package com.viajesglobal.method;

import com.viajesglobal.dto.ReservaDTO;
import java.util.List;

public interface ReservaMethod {
    public String saveReserva(ReservaDTO reservaDTO);
    public String updateReserva(Integer idReserva, ReservaDTO reservaDTO);
    public String deleteReserva(Integer idReserva);
    public List<ReservaDTO> getReservas();
}
