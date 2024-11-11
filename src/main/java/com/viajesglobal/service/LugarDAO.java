package com.viajesglobal.service;

import com.viajesglobal.dto.LugarDTO;
import com.viajesglobal.entity.Lugar;
import com.viajesglobal.method.LugarMethod;
import com.viajesglobal.repository.LugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LugarDAO implements LugarMethod {

    @Autowired
    private LugarRepository lugarRepository;


    /**
     * @param lugar
     * @return
     */
    @Override
    public String insertarLugar(LugarDTO lugar) {
        try {
            Lugar lugarInsertar = new Lugar(lugar.getNombre(),lugar.getPais(),lugar.getCiudad());
            lugarRepository.save(lugarInsertar);
            return "Lugar insertado correctamente";

        }catch (Exception e){
            return e.getMessage();
        }


    }

    /**
     * @param id
     * @return
     */
    @Override
    public LugarDTO getLugar(Integer id) {
        Lugar lugarOptional =lugarRepository.findById(id).orElse(null);
        if(lugarOptional==null){
            return null;
        }else{
            return new LugarDTO(lugarOptional.getIdLugar(),lugarOptional.getNombre(),lugarOptional.getPais(),lugarOptional.getCiudad());
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public String deleteLugar(Integer id) {
        try {
            lugarRepository.deleteById(id);
            return "Lugar eliminado correctamente";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    /**
     * @return
     */
    @Override
    public List<LugarDTO> getLugars() {
        List<Lugar> lugarOptional =lugarRepository.findAll();
        List<LugarDTO> lugarDTOList =new ArrayList<>();
        for(Lugar lugar:lugarOptional){
            lugarDTOList.add(new LugarDTO(lugar.getIdLugar(),lugar.getNombre(),lugar.getPais(),lugar.getCiudad()));
        }
        return lugarDTOList;
    }
}
