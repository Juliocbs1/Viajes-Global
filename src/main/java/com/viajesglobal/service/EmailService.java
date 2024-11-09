package com.viajesglobal.service;

import com.viajesglobal.dto.EmailDTO;
import com.viajesglobal.dto.UsuarioDTO;
import com.viajesglobal.method.EmailInterface;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Service
public class EmailService implements EmailInterface {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        super();
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void enviarCorreo(EmailDTO email, UsuarioDTO usuario) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(email.getDestinatario()); //recibe el destino
            helper.setSubject(email.getAsunto()); // recibe el asunto

            org.thymeleaf.context.Context context = new org.thymeleaf.context.Context();
            context.setVariable("mensaje", email.getMensaje());
            context.setVariable("usuarioid", usuario.getIdUsuario());
            context.setVariable("usuaarionombre", usuario.getNombre());
            context.setVariable("usuarioteleono", usuario.getTelefono());
            context.setVariable("usuariocontrasena", usuario.getContrasena());

            String contenidoHtml = templateEngine.process("RespuestaEmail", context);
            helper.setText(contenidoHtml, true);

            javaMailSender.send(message);

        }catch (Exception e) {
            e.printStackTrace(); // Agrega esto para ver el error completo
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage());
        }
    }
}
