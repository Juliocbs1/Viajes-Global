package com.viajesglobal.controller;

import com.viajesglobal.service.ReservaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    ReservaDAO reservaDAO;

    @PostMapping("/save-reserva")
    public String saveReserva(){
        return "index";
    }

}
