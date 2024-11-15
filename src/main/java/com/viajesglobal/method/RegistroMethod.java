package com.viajesglobal.method;



import com.viajesglobal.dto.UsuarioDTO;

import java.util.List;

public interface RegistroMethod {

    public String saveUsuario(UsuarioDTO usuarioDTO);
    public String updateUsuario(Long idUsario, UsuarioDTO usuarioDTO);
    public String deleteUsuario(Long idUsuario);
    public List<UsuarioDTO> getUsuarios();
    UsuarioDTO getUsuarioPorId(Long idUsuario);

}
