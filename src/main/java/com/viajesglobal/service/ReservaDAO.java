package com.viajesglobal.service;

import com.viajesglobal.dto.ReservaDTO;
import com.viajesglobal.entity.Reserva;
import com.viajesglobal.method.ReservaMethod;
import com.viajesglobal.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaDAO implements ReservaMethod {
    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public String saveReserva(ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva(reservaDTO.getIdUsuario(),reservaDTO.getIdPaquete(),reservaDTO.getEstado(),reservaDTO.getTotalPago());
        try {
            reservaRepository.save(reserva);
            return "Reserva Guardada!";
        } catch (Exception e) {
            return "Error en la reserva";
        }


    }
}
