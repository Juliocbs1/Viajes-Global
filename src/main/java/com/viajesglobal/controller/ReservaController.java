package com.viajesglobal.controller;


import com.viajesglobal.dto.LugarDTO;
import com.viajesglobal.service.LugarDAO;
import com.viajesglobal.service.ReservaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    private ReservaDAO reservaDAO;

    @Autowired
    private LugarDAO lugarDAO;

    //envio de datos a reserva.html
    @PostMapping("/buscar-vuelos")
    public String buscarVuelos(
            @RequestParam String tipoViaje, // Ida y vuelta o solo ida
            @RequestParam Integer origen, // ID del origen
            @RequestParam Integer destino, // ID del destino
            @RequestParam String fechaIda, // Fecha de ida
            @RequestParam(required = false) String fechaVuelta, // Fecha de vuelta (opcional)
            @RequestParam Integer cantidadPasajeros, // Cantidad de pasajeros
            Model model) {

        LugarDTO origenLugar = lugarDAO.getLugar(origen);
        LugarDTO destinoLugar = lugarDAO.getLugar(destino);
        if(origenLugar == null && destinoLugar == null) {

            return "redirect:/usuario";
        }else if(origenLugar == destinoLugar) {

            return "redirect:/usuario";
        }else {


            model.addAttribute("tipoViaje", tipoViaje);
            model.addAttribute("origenCiudad", origenLugar.getCiudad());
            model.addAttribute("origenPais",origenLugar.getPais() );
            model.addAttribute("destinoCiudad",  destinoLugar.getCiudad() );
            model.addAttribute("destinoPais",  destinoLugar.getPais() );
            model.addAttribute("fechaIda", fechaIda);
            model.addAttribute("fechaVuelta", fechaVuelta != null ? fechaVuelta : "");
            model.addAttribute("cantidadPasajeros", cantidadPasajeros);



            return "reserva";
        }
    }





}
