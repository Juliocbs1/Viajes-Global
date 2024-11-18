package com.viajesglobal.service;

import com.viajesglobal.dto.PagoDTO;
import com.viajesglobal.entity.Pago;
import com.viajesglobal.method.PagoMethod;
import com.viajesglobal.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PagoDAO implements PagoMethod{

    @Autowired
    private PagoRepository pagoRepository;


    @Override
    public String savePago(PagoDTO pagoDTO) {
        Pago pago = new Pago(
                pagoDTO.getIdPago(), pagoDTO.getIdReserva(), pagoDTO.getMonto(), pagoDTO.getEstadoPago(), pagoDTO.getIdTransaccionSecurepay()
        );
        try {
            System.out.println("Pago exitoso!");
            pagoRepository.save(pago);
            return "Pago exitoso!";
        }catch (Exception e){
            return "Error al guardar Pago!";
        }
    }

    @Override
    public List<PagoDTO> getPagos() {
        List<Pago> pagos = pagoRepository.findAll();
        List<PagoDTO> pagoDTOs = new ArrayList<>();
        for (Pago pago : pagos){
            pagoDTOs.add(new PagoDTO(
                    pago.getIdPago(),
                    pago.getIdReserva(),
                    pago.getMonto(),
                    pago.getEstadoPago(),
                    pago.getIdTransaccionSecurepay()
            ));
        }
        return pagoDTOs;
    }
}
