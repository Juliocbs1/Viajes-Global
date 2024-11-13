package com.viajesglobal.controller;

import com.viajesglobal.dto.PaqueteDTO;
import com.viajesglobal.dto.VueloDTO;
import com.viajesglobal.service.PaqueteDAO;
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
public class PaquetesController {

    @Autowired
    private PaqueteDAO paqueteDAO;

    @Autowired
    private VueloDAO vueloDAO;

    @GetMapping("/listarpaquetes")
    public String listarpaquetes(Model model){
        List<PaqueteDTO> lista = paqueteDAO.getPaquetes();
        model.addAttribute("paquetes", lista);
        model.addAttribute("paqueteDTO", new PaqueteDTO());

        List<VueloDTO> listavuelos = vueloDAO.listarVuelos();
        model.addAttribute("listavuelos", listavuelos);

        return "gestionarPromociones";
    }

    @PostMapping("/guardar-paquete")
    public String savePaquete(@ModelAttribute PaqueteDTO paqueteDTO, Model model){
        String resultado = paqueteDAO.savePaquete(paqueteDTO);
        System.out.println(resultado);
        return "redirect:/listarpaquetes";
    }

    @GetMapping("/borrarPaquete/{id}")
    public String borrarPaquete(@PathVariable int id, Model model){
        paqueteDAO.deletePaquete(id);
        return "redirect:/listarpaquetes";
    }


}
