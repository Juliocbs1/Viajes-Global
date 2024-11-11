package com.viajesglobal.service;

import com.viajesglobal.dto.VueloDTO;
import com.viajesglobal.entity.Vuelo;
import com.viajesglobal.method.VueloMethod;
import com.viajesglobal.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VueloDAO implements VueloMethod {

    @Autowired
    private VueloRepository vueloRepository;

    /**
     * @param vuelo
     * @return
     */
    @Override
    public String insertarVuelo(VueloDTO vuelo) {
        try{
            Vuelo vueloAux =new Vuelo(vuelo.getIdRuta(),vuelo.getNumeroVuelo(),vuelo.getFechaSalida(),vuelo.getAsientosTotales(),vuelo.getAsientosDisponibles(),vuelo.getCostoAsiento());
            vueloRepository.save(vueloAux);
            return "Vuelo guardado";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public VueloDTO getVuelo(Integer id) {
        Vuelo vueloAux = vueloRepository.findById(id).orElse(null);
        if(vueloAux == null){
            return null;
        }
        return new VueloDTO(vueloAux.getIdRuta(),vueloAux.getNumeroVuelo(),vueloAux.getFechaSalida(),vueloAux.getAsientosTotales(),vueloAux.getAsientosDisponibles(),vueloAux.getCostoAsiento());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public String borrarVuelo(Integer id) {
        try {
            vueloRepository.deleteById(id);
            return "Vuelo eliminado";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    /**
     * @param id
     * @param vuelo
     * @return
     */
    @Override
    public String modificarVuelo(Integer id, VueloDTO vuelo) {
        Vuelo vueloAux = vueloRepository.findById(id).orElse(null);
        if(vueloAux == null){
            return null;
        }else{

            vueloAux.setIdRuta(vuelo.getIdRuta());
            vueloAux.setNumeroVuelo(vuelo.getNumeroVuelo());
            vueloAux.setFechaSalida(vuelo.getFechaSalida());
            vueloAux.setAsientosTotales(vuelo.getAsientosTotales());
            vueloAux.setAsientosDisponibles(vuelo.getAsientosDisponibles());
            vueloAux.setCostoAsiento(vuelo.getCostoAsiento());
            vueloRepository.save(vueloAux);
            return "Vuelo modificado";
        }
    }

    /**
     * @return
     */
    @Override
    public List<VueloDTO> listarVuelos() {
        List<Vuelo> vueloAux = vueloRepository.findAll();
        List<VueloDTO> vueloDTOs = new ArrayList<>();
        for(Vuelo vuelo: vueloAux){
            vueloDTOs.add(new VueloDTO(vuelo.getIdRuta(),vuelo.getNumeroVuelo(),vuelo.getFechaSalida(),vuelo.getAsientosTotales(),vuelo.getAsientosDisponibles(),vuelo.getCostoAsiento()));

        }
        return vueloDTOs;
    }
}
