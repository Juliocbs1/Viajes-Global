package com.viajesglobal.controller;

import com.viajesglobal.dto.ReservaDTO;
import com.viajesglobal.estado.ReservaEstado;
import com.viajesglobal.service.ReservaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    ReservaDAO reservaDAO;



    @GetMapping("/save-reserva")
    public String saveReserva(){
        System.out.println(reservaDAO.saveReserva( new ReservaDTO(
                1,             // idUsuario
                1,             // idPaquete (o 0 si no hay paquete)
                1,             // idRuta (o 0 si no hay ruta)
                ReservaEstado.Confirmada,
                2000,          // totalPago
                Date.valueOf("2024-11-05"),    // fechaInicio
                Date.valueOf("2024-11-05"),    // fechaFin
                1,             // idVuelo
                2              // cantidadAsientos
        )));

        return "index";
    }

    @GetMapping("/update-reserva")
    public String actalizarReserva(){
        System.out.println(reservaDAO.updateReserva(2,new ReservaDTO(
                1,             // idUsuario
                1,             // idPaquete (o 0 si no hay paquete)
                0,             // idRuta (o 0 si no hay ruta)
                ReservaEstado.Confirmada,
                2000,          // totalPago
                Date.valueOf("2024-11-05"),    // fechaInicio
                Date.valueOf("2024-11-05"),    // fechaFin
                1,             // idVuelo
                2              // cantidadAsientos
        )));
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
