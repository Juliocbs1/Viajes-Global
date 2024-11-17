package com.viajesglobal.controller;

import com.viajesglobal.dto.LugarDTO;
import com.viajesglobal.dto.RutaDTO;
import com.viajesglobal.service.LugarDAO;
import com.viajesglobal.service.RutaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class RutaController {

    @Autowired
    private RutaDAO rutaDAO;

    @Autowired
    private LugarDAO lugarDAO;


    @GetMapping("/ListarLugares")
    public String ListarLugares(Model model){
        List<RutaDTO> rutas = rutaDAO.listarRuta();
        model.addAttribute("rutas", rutas);

        List<LugarDTO> lugar = lugarDAO.getLugars();
        model.addAttribute("lugar", lugar);

        Map<Integer, String> lugarMap = lugar.stream()
                .collect(Collectors.toMap(LugarDTO::getIdDestino, LugarDTO::getNombre));

        model.addAttribute("lugarMap", lugarMap);

        model.addAttribute("rutaDTO", new RutaDTO());
        return "gestionarRutas";
    }

    @PostMapping("/guardar-Ruta")
    public String guardarRuta(@ModelAttribute RutaDTO rutaDTO, Model model){
        String resultado = rutaDAO.insertarRuta(rutaDTO);
        System.out.println(resultado);
        return "redirect:/ListarLugares";
    }

    @GetMapping("/borrarRuta/{id}")
    public String borrarRuta(@PathVariable int id, Model model){
        rutaDAO.borrarRuta(id);
        return "redirect:/ListarLugares";
    }
}
