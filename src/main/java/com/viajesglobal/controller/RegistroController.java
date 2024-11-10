package com.viajesglobal.controller;


import com.viajesglobal.dto.EmailDTO;
import com.viajesglobal.dto.LugarDTO;
import com.viajesglobal.dto.UsuarioDTO;
import com.viajesglobal.estado.MensajesSMS;
import com.viajesglobal.estado.TipoNotificacion;
import com.viajesglobal.service.CustomEnumEditor;
import com.viajesglobal.service.EmailService;
import com.viajesglobal.service.LugarDAO;
import com.viajesglobal.service.RegistroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class RegistroController {

    @Autowired
    private RegistroDAO registroDAO;
    @Autowired
    private EmailService emailService;

    @Autowired
    private LugarDAO lugarDAO;




    @GetMapping
    public String mostrarFormulario(Model model) {
        List<LugarDTO> lugares = lugarDAO.getLugars();
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        model.addAttribute("lugares", lugares);

        return "index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(TipoNotificacion.class, "preferenciaNotificacion", new CustomEnumEditor(TipoNotificacion.class));
    }

    @PostMapping("/save-usuario")
    public String saveUsuario(@ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO, Model model) {
        String resultado = registroDAO.saveUsuario(usuarioDTO);
        model.addAttribute("registroMensaje", resultado);

        UsuarioDTO usuario = registroDAO.getUsuarioPorId(usuarioDTO.getIdUsuario());
        model.addAttribute("usuario", usuario);

        if (usuarioDTO.getPreferenciaNotificacion().equals(TipoNotificacion.CORREO)) {
            EmailDTO email = new EmailDTO();
            email.setDestinatario(usuarioDTO.getCorreo());
            email.setAsunto("Confirmación de Registro");
            email.setMensaje("Gracias por confiar en nosotros y registrarte. " +
                    "Te enviamos por este medio la información para que recuerdes tu usuario y contraseña.");

            System.out.println("mensaje enviado");
            emailService.enviarCorreo(email, usuario);
        }

        if (usuarioDTO.getPreferenciaNotificacion().equals(TipoNotificacion.SMS)) {
            MensajesSMS mensajesSMS = new MensajesSMS();
            String numero = usuarioDTO.getTelefono();
            String mensaje = "Bienviendo y gracias por registrate con nosotros, tu usuario es "
                    + usuarioDTO.getIdUsuario() + " y tu contraseña es" + usuarioDTO.getContrasena();

            mensajesSMS.enviarMensaje(mensaje, numero);
        }
        return "index";
    }
}
