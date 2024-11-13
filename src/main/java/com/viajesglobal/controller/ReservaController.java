package com.viajesglobal.controller;



import com.viajesglobal.dto.LugarDTO;

import com.viajesglobal.dto.ReservaDTO;
import com.viajesglobal.dto.RutaDTO;
import com.viajesglobal.dto.VueloDTO;
import com.viajesglobal.service.LugarDAO;
import com.viajesglobal.service.ReservaDAO;
import com.viajesglobal.service.RutaDAO;
import com.viajesglobal.service.VueloDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    private ReservaDAO reservaDAO;

    @Autowired
    private LugarDAO lugarDAO;

    @Autowired
    private VueloDAO vueloDAO;

    @Autowired
    private RutaDAO rutaDAO;



    //envio de datos a reserva.html
    @PostMapping("/buscar-vuelos")
    public String buscarVuelos(
            @RequestParam String tipoViaje, // Ida y vuelta o solo ida
            @RequestParam Integer origen, // ID del origen
            @RequestParam Integer destino, // ID del destino
            @RequestParam LocalDate fechaIda, // Fecha de ida
            @RequestParam(required = false) LocalDate fechaVuelta, // Fecha de vuelta (opcional)
            @RequestParam Integer cantidadPasajeros, // Cantidad de pasajeros
            Model model) {

        LugarDTO origenLugar = lugarDAO.getLugar(origen);
        LugarDTO destinoLugar = lugarDAO.getLugar(destino);

        // Validaciones iniciales
        if (origenLugar == null || destinoLugar == null) {
            System.out.println("Origen o destino no válido");
            return "redirect:/usuario";
        }
        if (origenLugar.equals(destinoLugar)) {
            System.out.println("El origen y destino son iguales");
            return "redirect:/usuario";
        }
        if (tipoViaje.equals("idaYvuelta") && (fechaVuelta == null || fechaIda.isAfter(fechaVuelta))) {
            System.out.println("La fecha de ida es posterior a la de regreso o fecha de vuelta no especificada");
            return "redirect:/usuario";
        }


        List<VueloDTO> vuelosDeIda = new ArrayList<>();
        List<VueloDTO> vuelosDeRegreso = new ArrayList<>();
        List<VueloDTO> vueloDTOList = vueloDAO.listarVuelos();

        for (VueloDTO vueloDTO : vueloDTOList) {
            RutaDTO rutaDTO = rutaDAO.getRuta(vueloDTO.getIdRuta());
            if (rutaDTO == null) continue;


            if (vueloDTO.getFechaSalida().toLocalDate().equals(fechaIda) &&
                    rutaDTO.getIdOrigen() == origen && rutaDTO.getIdDestino() ==destino) {
                vuelosDeIda.add(vueloDTO);
                System.out.println("Vuelo de ida encontrado");
            }


            if (tipoViaje.equals("idaYvuelta") && fechaVuelta != null &&
                    vueloDTO.getFechaSalida().toLocalDate().equals(fechaVuelta) &&
                    rutaDTO.getIdOrigen() == destino  && rutaDTO.getIdDestino()==origen) {
                vuelosDeRegreso.add(vueloDTO);
                System.out.println("Vuelo de regreso encontrado");
            }
        }

        // Verificación final para vuelos de ida y vuelta
        if ((tipoViaje.equals("idaYvuelta") && !vuelosDeIda.isEmpty() && !vuelosDeRegreso.isEmpty()) ||
                (tipoViaje.equals("soloIda") && !vuelosDeIda.isEmpty())) {

            model.addAttribute("tipoViaje", tipoViaje);
            model.addAttribute("origenCiudad", origenLugar.getCiudad());
            model.addAttribute("origenPais", origenLugar.getPais());
            model.addAttribute("destinoCiudad", destinoLugar.getCiudad());
            model.addAttribute("destinoPais", destinoLugar.getPais());
            model.addAttribute("fechaIda", fechaIda);
            model.addAttribute("fechaVuelta", fechaVuelta != null ? fechaVuelta : "");
            model.addAttribute("cantidadPasajeros", cantidadPasajeros);

            // Pasar lista de vuelos de ida y, si aplica, de regreso
            model.addAttribute("vuelosDeIda", vuelosDeIda);


            if (tipoViaje.equals("idaYvuelta")) {
                model.addAttribute("vuelosDeRegreso", vuelosDeRegreso);
            }

            return "reserva";
        }

        return "redirect:/usuario";
        }
}




