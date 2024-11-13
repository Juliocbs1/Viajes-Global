package com.viajesglobal.controller;


import com.viajesglobal.dto.EmailDTO;
import com.viajesglobal.dto.LugarDTO;
import com.viajesglobal.dto.PaqueteDTO;
import com.viajesglobal.dto.UsuarioDTO;
import com.viajesglobal.estado.MensajesSMS;
import com.viajesglobal.estado.TipoNotificacion;
import com.viajesglobal.service.EmailService;
import com.viajesglobal.service.LugarDAO;
import com.viajesglobal.service.PaqueteDAO;
import com.viajesglobal.service.RegistroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private PaqueteDAO paqueteDAO;

    @GetMapping
    public String mostrarFormulario(Model model) {
        List<LugarDTO> lugares = lugarDAO.getLugars();
        model.addAttribute("usuarioDTO", new UsuarioDTO());

        List<PaqueteDTO> paqueteDTOS = paqueteDAO.getPaquetes();
        if (paqueteDTOS.size() >5 ){
            paqueteDTOS = paqueteDTOS.subList(0, 5);
        }
        model.addAttribute("paquetes", paqueteDTOS);

        model.addAttribute("lugares", lugares);
        return "index";
    }

    @PostMapping("/save-usuario")
    public String saveUsuario(@ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO, Model model) {
        String resultado = registroDAO.saveUsuario(usuarioDTO);
        model.addAttribute("registroMensaje", resultado);

        UsuarioDTO usuario = registroDAO.getUsuarioPorId(usuarioDTO.getIdUsuario());
        model.addAttribute("usuario", usuario);


        System.out.println("Preferencia de notificación recibida: " + usuarioDTO.getPreferenciaNotificacion());

        if (usuarioDTO.getPreferenciaNotificacion().equals(TipoNotificacion.CORREO)) {
            EmailDTO email = new EmailDTO();
            email.setDestinatario(usuarioDTO.getCorreo());
            email.setAsunto("Confirmación de Registro");
            email.setMensaje("Gracias por confiar en nosotros y registrarte. Te enviamos por este medio la información para que recuerdes tu usuario y contraseña.");
            System.out.println("Mensaje enviado por correo");
            emailService.enviarCorreo(email, usuario);
        }

        if (usuarioDTO.getPreferenciaNotificacion().equals(TipoNotificacion.SMS)) {
            MensajesSMS mensajesSMS = new MensajesSMS();
            String numero = usuarioDTO.getTelefono();
            String mensaje = "Bienvenido y gracias por registrarte con nosotros, tu usuario es "
                    + usuarioDTO.getIdUsuario() + " y tu contraseña es " + usuarioDTO.getContrasena();
            System.out.println("Mensaje enviado por SMS");
            mensajesSMS.enviarMensaje(mensaje, numero);
        }
        return "index";
    }
}
