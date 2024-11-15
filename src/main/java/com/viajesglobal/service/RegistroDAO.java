package com.viajesglobal.service;

import com.viajesglobal.dto.ReservaDTO;
import com.viajesglobal.dto.UsuarioDTO;
import com.viajesglobal.entity.Reserva;
import com.viajesglobal.entity.Usuario;
import com.viajesglobal.method.RegistroMethod;
import com.viajesglobal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroDAO implements RegistroMethod {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public String saveUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario(
                usuarioDTO.getIdUsuario(),
                usuarioDTO.getNombre(),
                usuarioDTO.getCorreo(),
                usuarioDTO.getTelefono(),
                usuarioDTO.getContrasena(),
                usuarioDTO.getPreferenciaNotificacion());
        try {
            usuarioRepository.save(usuario);
            return "Usuario Guardado!";
        } catch (Exception e) {
            return "Error al guardar";
        }
    }

    @Override
    public String updateUsuario(Long idUsario, UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsario);
        if (usuario.isPresent()) {
            Usuario usuarioAux = usuario.get();
            usuarioAux.setNombre(usuarioDTO.getNombre());
            usuarioAux.setCorreo(usuarioDTO.getCorreo());
            usuarioAux.setTelefono(usuarioDTO.getTelefono());
            usuarioAux.setContrasena(usuarioDTO.getContrasena());
            usuarioRepository.save(usuarioAux);
            return "Usuario Actualizado!";
        }
        return "Error al actualizar";
    }

    @Override
    public String deleteUsuario(Long idUsuario) {
        if (usuarioRepository.existsById(idUsuario)) {
            usuarioRepository.deleteById(idUsuario);
            return "Usuario Eliminado!";
        }
        return "Error al eliminar";
    }

    @Override
    public List<UsuarioDTO> getUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuariosDTO.add(new UsuarioDTO(
                    usuario.getIdUsuario(),
                    usuario.getNombre(),
                    usuario.getCorreo(),
                    usuario.getTelefono(),
                    usuario.getContrasena(),
                    usuario.getPreferenciaNotificacion()
            ));
        }
        return usuariosDTO;
    }

    @Override
    public UsuarioDTO getUsuarioPorId(Long idUsuario) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            return new UsuarioDTO(
                    usuario.getIdUsuario(),
                    usuario.getNombre(),
                    usuario.getCorreo(),
                    usuario.getTelefono(),
                    usuario.getContrasena(),
                    usuario.getPreferenciaNotificacion()
            );
        }
        return null;
    }
}
