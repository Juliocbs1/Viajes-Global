package com.viajesglobal.controller;

import com.viajesglobal.dto.UsuarioDTO;
import com.viajesglobal.service.RegistroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminUsuarioController {

    @Autowired
    private RegistroDAO registroDAO;

    @GetMapping("/admin")
    public String admin() {
        return "PaginaRedireccion";
    }

    @GetMapping("/listausuarios")
    public String listarUsuarios(Model model) {
        List<UsuarioDTO> lista = registroDAO.getUsuarios();
        model.addAttribute("usuarios",  lista);
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "gestionarClientes";
    }

    @PostMapping("/guardar-usuario")
    public String saveUsuario(@ModelAttribute UsuarioDTO usuarioDTO,Model model) {
        String resultado = registroDAO.saveUsuario(usuarioDTO);
        System.out.println("usuario guardado: " + resultado);
        return "redirect:/listausuarios";
    }

    @GetMapping("/borrarusuario/{id}")
    public String borrarUsuario(@PathVariable long id) {
        registroDAO.deleteUsuario(id);
        return "redirect:/listausuarios";
    }

}
