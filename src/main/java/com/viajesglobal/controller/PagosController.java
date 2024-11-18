package com.viajesglobal.controller;

import com.viajesglobal.dto.PagoDTO;
import com.viajesglobal.estado.PagoEstado;
import com.viajesglobal.service.PagoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PagosController {

    @Autowired
    private PagoDAO pagoDAO;

    // Método para mostrar la página de pagos
    @GetMapping("/pagos")
    public String pagos() {
        return "pagos"; // Retorna la vista "pagos.html" o "pagos.jsp"
    }

    // Método para procesar el pago
    @PostMapping("/realizarpago")
    public String savePago(
            @RequestParam("numero") String numeroTarjeta,
            @RequestParam("mes") int mesExpiracion,
            @RequestParam("year") int anioExpiracion,
            @RequestParam("ccv") String ccv,
            @ModelAttribute("pagoDTO") PagoDTO pagoDTO,
            Model model) {

        if (validarTarjeta(numeroTarjeta, mesExpiracion, anioExpiracion, ccv)) {
            model.addAttribute("error", "La tarjeta no es válida. Verifique los datos ingresados.");
            System.out.println(numeroTarjeta+" error");
            return "pagos"; // Retorna la vista "pagos" con el mensaje de error

        }
        PagoDTO pago = new PagoDTO();
        pago.setIdReserva(1);
        pago.setMonto(pagoDTO.getMonto());
        pago.setEstadoPago(PagoEstado.Completado);
        pago.setIdTransaccionSecurepay(java.util.UUID.randomUUID().toString());
        // Guardar el pago usando el DAO
        System.out.println(numeroTarjeta);
        String resultado = pagoDAO.savePago(pago);

        // Agregar el resultado al modelo (opcional, para mostrar un mensaje)
        model.addAttribute("mensaje", "Pago realizado con éxito: " + resultado);

        // Redirigir a una página específica o mostrar un mensaje
        return "redirect:/pagos"; // Redirige a la lista de pagos
    }

    // Método para listar todos los pagos
    @GetMapping("/listarpagos")
    public String listar(Model model) {
        // Obtener la lista de pagos
        List<PagoDTO> lista = pagoDAO.getPagos();

        // Agregar datos al modelo para la vista
        model.addAttribute("pagos", lista);
        model.addAttribute("pago", new PagoDTO());

        // Retornar la vista
        return "listarpagos";
    }

    private boolean validarTarjeta(String numeroTarjeta, int mesExpiracion, int anioExpiracion, String ccv) {
        int suma = 0;
        boolean esPar = false;

        // Recorrer el número de tarjeta desde el final (invertido)
        for (int i = numeroTarjeta.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numeroTarjeta.charAt(i));

            // Si es una posición impar, multiplicar por 2
            if (esPar) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9; // Restar 9 si el número es mayor que 9
                }
            }

            suma += digito;
            esPar = !esPar; // Alternar entre las posiciones impares y pares
        }

        // El número es válido si la suma es un múltiplo de 10
        return suma % 10 == 0;


    }

}
