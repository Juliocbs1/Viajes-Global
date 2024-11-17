package com.viajesglobal.controller;

import com.viajesglobal.dto.LugarDTO;
import com.viajesglobal.dto.RutaDTO;
import com.viajesglobal.dto.VueloDTO;
import com.viajesglobal.service.LugarDAO;
import com.viajesglobal.service.RutaDAO;
import com.viajesglobal.service.VueloDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class VueloController {

    @Autowired
    private VueloDAO vueloDAO;

    @Autowired
    private RutaDAO rutaDAO;

    @Autowired
    private LugarDAO lugarDAO;


    @GetMapping("/listarvuelos")
    public String listarVuelos(Model model) {
        List<VueloDTO> vuelos = vueloDAO.listarVuelos();
        model.addAttribute("vuelos", vuelos);

        List<RutaDTO> rutas = rutaDAO.listarRuta();
        model.addAttribute("rutas", rutas);

        model.addAttribute("vueloDTO", new VueloDTO());
        return "gestionarVuelos";
    }

    @PostMapping("/guardar-vuelo")
    public String guardarVuelo(@ModelAttribute VueloDTO vuelo, Model model) {
        String resultado = vueloDAO.insertarVuelo(vuelo);
        System.out.println(resultado);
        return "redirect:/listarvuelos";
    }

    @GetMapping("/borrarVuelo/{id}")
    public String borrarVuelo(@PathVariable int id, Model model) {
        vueloDAO.borrarVuelo(id);
        return "redirect:/listarvuelos";
    }


}
