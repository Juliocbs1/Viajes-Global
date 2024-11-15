package com.viajesglobal.service;

import com.viajesglobal.dto.PaqueteDTO;
import com.viajesglobal.entity.Paquete;
import com.viajesglobal.method.PaqueteMethod;
import com.viajesglobal.repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaqueteDAO implements PaqueteMethod {

    @Autowired
    private PaqueteRepository paqueteRepository;

    @Override
    public String savePaquete(PaqueteDTO paqueteDTO) {
        Paquete paquete = new Paquete(
                paqueteDTO.getIdPaquete(),
                paqueteDTO.getNombre(),
                paqueteDTO.getDescripcion(),
                paqueteDTO.getPrecio(),
                paqueteDTO.getDisponibilidad(),
                paqueteDTO.getIdVuelo()
        );
        try {
            System.out.println(paqueteDTO.getIdPaquete()+ " " + paqueteDTO.getNombre() + " " + paqueteDTO.getDescripcion() + " " +
                    paqueteDTO.getPrecio() + " " + paqueteDTO.getDisponibilidad() + " " + paqueteDTO.getIdVuelo());
            paqueteRepository.save(paquete);
            return "Paquete creada correctamente";
        }catch (Exception e){
            return "Error al crear la paquete";
        }
    }

    @Override
    public String updatePaquete(int idPaquete, PaqueteDTO paqueteDTO) {
        Optional<Paquete> paquete = paqueteRepository.findById(idPaquete);
        if (paquete.isPresent()) {
            Paquete paqueteAux = paquete.get();
            paqueteAux.setNombre(paqueteDTO.getNombre());
            paqueteAux.setDescripcion(paqueteDTO.getDescripcion());
            paqueteAux.setPrecio(paqueteDTO.getPrecio());
            paqueteAux.setDescripcion(paqueteDTO.getDescripcion());
            paqueteAux.setIdVuelo(paqueteDTO.getIdVuelo());
            paqueteRepository.save(paqueteAux);
            return "Paquete actualizada correctamente";
        }

        return "Error al actualizar paquete";
    }

    @Override
    public String deletePaquete(int idPaquete) {
        if (paqueteRepository.existsById(idPaquete)) {
            paqueteRepository.deleteById(idPaquete);
            return "Paquete eliminado correctamente";
        }
        return "Error al eliminar el paquete";
    }

    @Override
    public List<PaqueteDTO> getPaquetes() {
        List<Paquete> paquetes = paqueteRepository.findAll();
        List<PaqueteDTO> paqueteDTOs = new ArrayList<>();
        for (Paquete paquete : paquetes) {
            paqueteDTOs.add(new PaqueteDTO(
                    paquete.getIdPaquete(),
                    paquete.getNombre(),
                    paquete.getDescripcion(),
                    paquete.getPrecio(),
                    paquete.getDisponibilidad(),
                    paquete.getIdVuelo()
            ));
        }
        return paqueteDTOs;
    }

    @Override
    public PaqueteDTO getPaquete(int idPaquete) {
        Paquete paquete = paqueteRepository.findById(idPaquete).orElse(null);
        if (paquete != null) {
            return new PaqueteDTO(paquete.getIdPaquete(),paquete.getNombre(),paquete.getDescripcion(),paquete.getPrecio(),paquete.getDisponibilidad(),paquete.getIdVuelo());
        }

        return null;
    }
}
