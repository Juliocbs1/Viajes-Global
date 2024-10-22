package com.viajesglobal.controller;

import com.viajesglobal.dto.ReservaDTO;
import com.viajesglobal.estado.ReservaEstado;
import com.viajesglobal.service.ReservaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    ReservaDAO reservaDAO;

    @GetMapping("/save-reserva")
    public String saveReserva(){
        System.out.println(reservaDAO.saveReserva(new ReservaDTO(2, 1, ReservaEstado.Confirmada, 20000)));

        return "index";
    }

    @GetMapping("/update-reserva")
    public String actalizarReserva(){
        System.out.println(reservaDAO.updateReserva(2,new ReservaDTO(1,2,ReservaEstado.Cancelada,20000)));
        return "index";
    }

    @GetMapping("/delete-reserva")
    public String deleteReserva(){
        System.out.println(reservaDAO.deleteReserva(2));
        return "index";
    }
    @GetMapping("/list-reservas")
    public String listReservas(){
        List<ReservaDTO>reservas = reservaDAO.getReservas();
        for(ReservaDTO reserva : reservas){
            System.out.println(reserva);
        }
        return "index";
    }

}
