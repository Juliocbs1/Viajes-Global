package com.viajesglobal.service;

import com.viajesglobal.dto.RutaDTO;
import com.viajesglobal.entity.Ruta;
import com.viajesglobal.method.RutaMethod;
import com.viajesglobal.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RutaDAO implements RutaMethod {

    @Autowired
    private RutaRepository rutaRepository;
    /**
     * @param ruta
     * @return
     */
    @Override
    public String insertarRuta(RutaDTO ruta) {
        Ruta rutaAux = new Ruta(
                ruta.getIdRuta(),
                ruta.getIdOrigen(),
                ruta.getIdDestino(),
                ruta.getDuracionEstimada());
        try{
            rutaRepository.save(rutaAux);
            return "Ruta guardada";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    /**
     * @param id
     * @return
     */
    @Override
    public String borrarRuta(Integer id) {
        Ruta rutaAux = rutaRepository.findById(id).orElse(null);
        if (rutaAux != null) {
            rutaRepository.delete(rutaAux);
            return "Ruta eliminada";
        }else{
            return "Ruta no encontrada";
        }

    }

    /**
     * @param id
     * @param ruta
     * @return
     */
    @Override
    public String modificarRuta(Integer id, RutaDTO ruta) {
        Ruta rutaAux = rutaRepository.findById(id).orElse(null);
        if (rutaAux != null) {
            rutaAux.setIdOrigen(ruta.getIdOrigen());
            rutaAux.setIdDestino(ruta.getIdDestino());
            rutaAux.setDuracionEstimada(ruta.getDuracionEstimada());
            rutaRepository.save(rutaAux);
            return "Ruta actualizada";
        }else{
            return "Ruta no encontrada";
        }

    }

    /**
     * @return
     */
    @Override
    public List<RutaDTO> listarRuta() {
        List<Ruta> rutas = rutaRepository.findAll();
        List<RutaDTO> rutaDTOs = new ArrayList<>();
        for (Ruta rutaAux : rutas) {
            rutaDTOs.add(new RutaDTO(rutaAux.getIdRuta(),rutaAux.getIdOrigen(),rutaAux.getIdDestino(),rutaAux.getDuracionEstimada()));
        }
        return rutaDTOs;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public RutaDTO getRuta(Integer id) {
        Ruta rutaAux = rutaRepository.findById(id).orElse(null);
        if (rutaAux != null) {
            return new RutaDTO(rutaAux.getIdRuta(),rutaAux.getIdOrigen(),rutaAux.getIdDestino(),rutaAux.getDuracionEstimada());
        }else{
            return null;
        }


    }

    /**
     * @param idOrigen
     * @param idDestino
     * @return
     */
    @Override
    public List<RutaDTO> findByIdOrigenAndIdDestino(Integer idOrigen, Integer idDestino) {
        List<Ruta> rutas = rutaRepository.findByIdOrigenAndIdDestino(idOrigen, idDestino);
        List<RutaDTO>rutasDTOs = new ArrayList<>();
        for (Ruta rutaAux : rutas) {
            rutasDTOs.add(new RutaDTO(rutaAux.getIdRuta(),rutaAux.getIdOrigen(),rutaAux.getIdDestino(),rutaAux.getDuracionEstimada()));
        }
        return rutasDTOs;
    }
}
