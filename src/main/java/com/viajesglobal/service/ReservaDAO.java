package com.viajesglobal.service;

import com.viajesglobal.dto.ReservaDTO;
import com.viajesglobal.entity.Reserva;
import com.viajesglobal.method.ReservaMethod;
import com.viajesglobal.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaDAO implements ReservaMethod {
    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public String saveReserva(ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva(reservaDTO.getIdUsuario(),reservaDTO.getIdPaquete(),reservaDTO.getEstado(),reservaDTO.getTotalPago(),reservaDTO.getFechaInicio(),reservaDTO.getFechaFin(),reservaDTO.getIdVuelo(),reservaDTO.getCantidadAiento());

        try {
            reservaRepository.save(reserva);
            return "Reserva Guardada!";
        } catch (Exception e) {
            return "Error en la reserva";
        }


    }

    @Override
    public String updateReserva(Integer idReserva, ReservaDTO reservaDTO) {
        Optional<Reserva> reserva = reservaRepository.findById(idReserva);
        if(reserva.isPresent()) {
            Reserva reservaAux = reserva.get();
            reservaAux.setEstado(reservaDTO.getEstado());
            reservaAux.setTotalPago(reservaDTO.getTotalPago());
            reservaAux.setIdUsuario(reservaDTO.getIdUsuario());
            reservaAux.setFechaInicio(reservaDTO.getFechaInicio());
            reservaAux.setFechaFin(reservaDTO.getFechaFin());
            reservaAux.setIdVuelo(reservaDTO.getIdVuelo());

            reservaRepository.save(reservaAux);
            return "Reserva Actualizada!";
        }
        return "Error en la reserva";


    }

    @Override
    public String deleteReserva(Integer idReserva) {
        if(reservaRepository.existsById(idReserva)) {
            reservaRepository.deleteById(idReserva);
            return "Reserva Eliminada!";
        }
        return "Error en la reserva";
    }



    @Override
    public List<ReservaDTO> getReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for(Reserva reserva : reservas) {
            reservasDTO.add(new ReservaDTO(reserva.getIdUsuario(),reserva.getIdPaquete(),reserva.getEstado(),reserva.getTotalPago(),reserva.getFechaInicio(),reserva.getFechaFin(),reserva.getIdVuelo(),reserva.getCantidadAiento()));
        }
        return reservasDTO;
    }
}
