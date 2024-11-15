package com.viajesglobal.method;

import com.viajesglobal.dto.LugarDTO;
import com.viajesglobal.entity.Lugar;

import java.util.List;

public interface LugarMethod {
    public String  insertarLugar(LugarDTO lugar);
    public LugarDTO getLugar(Integer id);
    public String deleteLugar(Integer id);
    public List<LugarDTO> getLugars();
}
