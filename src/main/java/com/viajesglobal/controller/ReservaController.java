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
            @RequestParam(required = false) String fechaVuelta, // Fecha de vuelta (opcional)
            @RequestParam Integer cantidadPasajeros, // Cantidad de pasajeros
            Model model) {

        LugarDTO origenLugar = lugarDAO.getLugar(origen);
        LugarDTO destinoLugar = lugarDAO.getLugar(destino);
        if (origenLugar == null && destinoLugar == null) {

            return "redirect:/usuario";
        } else if (origenLugar == destinoLugar) {

            return "redirect:/usuario";
        } else {

            List<VueloDTO> vueloDTOList = vueloDAO.listarVuelos();
            List<VueloDTO> vuelosParaReserva = new ArrayList<>();

            for (VueloDTO vueloDTO : vueloDTOList) {
                //Voy a convertir localdatetime de vuelo a localdate para solo compara fechas sin tener
                //en cuenta la hora

                if (vueloDTO.getFechaSalida().toLocalDate().equals(fechaIda)) {
                    RutaDTO rutaDTO = rutaDAO.getRuta(vueloDTO.getIdRuta());
                    //Verifica si existe una ruta con ese id
                    if (rutaDTO != null) {
                        //Verifico que coincidad el origen y destino con la ruta
                        if (rutaDTO.getIdOrigen() == origen && (rutaDTO.getIdDestino() == destino)) {
                            //Si finalemnte pasa todos los filtros agregamos a la lista
                            vuelosParaReserva.add(vueloDTO);
                        }
                    }


                }
            }


            if (!vuelosParaReserva.isEmpty()) {
                model.addAttribute("tipoViaje", tipoViaje);
                model.addAttribute("origenCiudad", origenLugar.getCiudad());
                model.addAttribute("origenPais", origenLugar.getPais());
                model.addAttribute("destinoCiudad", destinoLugar.getCiudad());
                model.addAttribute("destinoPais", destinoLugar.getPais());
                model.addAttribute("fechaIda", fechaIda);
                model.addAttribute("fechaVuelta", fechaVuelta != null ? fechaVuelta : "");
                model.addAttribute("cantidadPasajeros", cantidadPasajeros);
                model.addAttribute("vuelosParaReserva", vuelosParaReserva );





                return "reserva";

            } else {
                return "redirect:/usuario";
            }

        }
    }


}
