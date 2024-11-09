package com.viajesglobal.method;

import com.viajesglobal.dto.EmailDTO;
import com.viajesglobal.dto.UsuarioDTO;

public interface EmailInterface {

    public void enviarCorreo(EmailDTO email, UsuarioDTO usuario);

}
