package com.viajesglobal.controller;



import com.viajesglobal.dto.*;

import com.viajesglobal.estado.ReservaEstado;
import com.viajesglobal.service.*;
import jakarta.annotation.PostConstruct;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    private ReservaDAO reservaDAO;

    @Autowired
    private LugarDAO lugarDAO;

    @Autowired
    private VueloDAO vueloDAO;

    @Autowired
    private RutaDAO rutaDAO;

    @Autowired
    private PaqueteDAO paqueteDAO;

    @Autowired
    private RegistroDAO registroDAO;

    private long idUsuario = 0;

    private int cantidadAsiento;


    @PostMapping("/login")
    public String login(@RequestParam("username")Long id, @RequestParam("password") String password, Model model) {
        UsuarioDTO usuarioDTO=registroDAO.getUsuarioPorId( id);
        if(usuarioDTO!=null) {
            if(usuarioDTO.getContrasena().equals(password)) {
                idUsuario=id;
                System.out.println("Usuario encontrado");
                model.addAttribute("errorMessage", "Usuario encontrado "+usuarioDTO.getNombre());
                return "redirect:/usuario";
            }
            model.addAttribute("errorMessage", "id o contraseña incorrectos.");
            System.out.println("Usuario no encontrado");
            return "redirect:/usuario";
        }
        model.addAttribute("errorMessage", "Usuario no encontrado");
        System.out.println("Usuario no encontrado");
        return "redirect:/usuario";

    }


    @PostMapping("/buscar-vuelos")
    public String buscarVuelos(
            @RequestParam String tipoViaje, // Ida y vuelta o solo ida
            @RequestParam Integer origen, // ID del origen
            @RequestParam Integer destino, // ID del destino
            @RequestParam LocalDate fechaIda, // Fecha de ida
            @RequestParam(required = false) LocalDate fechaVuelta, // Fecha de vuelta (opcional)
            @RequestParam Integer cantidadPasajeros, // Cantidad de pasajeros
            Model model) {
        if(idUsuario==0) {
            System.out.println("Usuario no encontrado");
            return "redirect:/usuario";
        }

        cantidadAsiento = 0;

        LugarDTO origenLugar = lugarDAO.getLugar(origen);
        LugarDTO destinoLugar = lugarDAO.getLugar(destino);


        if (origenLugar == null || destinoLugar == null) {
            System.out.println("Origen o destino no válido");
            return "redirect:/usuario";
        }
        if (origenLugar.equals(destinoLugar)) {
            System.out.println("El origen y destino son iguales");
            return "redirect:/usuario";
        }
        if (tipoViaje.equals("idaYvuelta") && (fechaVuelta == null || fechaIda.isAfter(fechaVuelta))) {
            System.out.println("La fecha de ida es posterior a la de regreso o fecha de vuelta no especificada");
            return "redirect:/usuario";
        }


        List<VueloDTO> vuelosDeIda = new ArrayList<>();
        List<VueloDTO> vuelosDeRegreso = new ArrayList<>();
        List<VueloDTO> vueloDTOList = vueloDAO.listarVuelos();

        for (VueloDTO vueloDTO : vueloDTOList) {
            RutaDTO rutaDTO = rutaDAO.getRuta(vueloDTO.getIdRuta());
            if (rutaDTO == null) continue;


            if (vueloDTO.getFechaSalida().toLocalDate().equals(fechaIda) &&
                    rutaDTO.getIdOrigen() == origen && rutaDTO.getIdDestino() == destino) {
                vuelosDeIda.add(vueloDTO);
                System.out.println("Vuelo de ida encontrado");
            }


            if (tipoViaje.equals("idaYvuelta") && fechaVuelta != null &&
                    vueloDTO.getFechaSalida().toLocalDate().equals(fechaVuelta) &&
                    rutaDTO.getIdOrigen() == destino && rutaDTO.getIdDestino() == origen) {
                vuelosDeRegreso.add(vueloDTO);
                System.out.println("Vuelo de regreso encontrado");
            }
        }


        if ((tipoViaje.equals("idaYvuelta") && !vuelosDeIda.isEmpty() && !vuelosDeRegreso.isEmpty()) ||
                (tipoViaje.equals("soloIda") && !vuelosDeIda.isEmpty())) {

            model.addAttribute("tipoViaje", tipoViaje);
            model.addAttribute("origenCiudad", origenLugar.getCiudad());
            model.addAttribute("origenPais", origenLugar.getPais());
            model.addAttribute("destinoCiudad", destinoLugar.getCiudad());
            model.addAttribute("destinoPais", destinoLugar.getPais());
            model.addAttribute("fechaIda", fechaIda);
            model.addAttribute("fechaVuelta", fechaVuelta != null ? fechaVuelta : "");
            model.addAttribute("cantidadPasajeros", cantidadPasajeros);


            model.addAttribute("vuelosDeIda", vuelosDeIda);


            cantidadAsiento = cantidadPasajeros;



            if (tipoViaje.equals("idaYvuelta")) {
                model.addAttribute("vuelosDeRegreso", vuelosDeRegreso);
            }

            return "reserva";
        }

        return "redirect:/usuario";
    }

    @PostMapping("/confirmar")
    public String confirmar(@RequestParam Integer vueloIda,  // ID del vuelo de ida seleccionado
                            @RequestParam(required = false) Integer vueloRegreso,  // ID del vuelo de regreso seleccionado (opcional)
                            Model model) {
        if(idUsuario ==0){
            System.out.println("Usuario no encontrado");
            return "redirect:/usuario";
        }

        System.out.println("ID vuelo ida "+vueloIda);
        System.out.println("ID vuelo vuelta "+vueloRegreso);

        List<ReservaDTO> reservas = new ArrayList<>();

        VueloDTO vueloDeIda = vueloDAO.getVuelo(vueloIda);

        if (vueloDeIda == null) {
            System.out.println("Vuelo de ida no encontrado");
            return "redirect:/usuario";
        }
        long costoVueloIda = vueloDeIda.getCostoAsiento();
        long totalPorVueloIda = costoVueloIda * cantidadAsiento;
        int asientosIda= vueloDeIda.getAsientosDisponibles()-cantidadAsiento;
        if(asientosIda <=0){
            System.out.println("Asientos no disponibles");
            return "redirect:/usuario";
        }
        vueloDAO.modificarVuelo(vueloDeIda.getIdRuta(),new VueloDTO(vueloDeIda.getIdRuta(),vueloDeIda.getNumeroVuelo(),vueloDeIda.getFechaSalida(),vueloDeIda.getAsientosTotales(),asientosIda,vueloDeIda.getCostoAsiento()));
        System.out.println(1+" "+ null+" "+ ReservaEstado.Pendiente+" "+ totalPorVueloIda+" "+ vueloDeIda.getIdVuelo()+" "+cantidadAsiento);
        ReservaDTO reservaIda = new ReservaDTO((int)idUsuario, null, ReservaEstado.Pendiente, totalPorVueloIda, vueloDeIda.getIdVuelo(), cantidadAsiento);
        reservas.add(reservaIda);



        if (vueloRegreso != null) {
            VueloDTO vueloDeRegreso = vueloDAO.getVuelo(vueloRegreso);
            if (vueloDeRegreso == null) {
                System.out.println("Vuelo de regreso no encontrado");
                return "redirect:/usuario";
            }


            long costoVueloRegreso = vueloDeRegreso.getCostoAsiento();
            long totalPorVueloRegreso = costoVueloRegreso * cantidadAsiento;
            int asientosRegreso= vueloDeRegreso.getAsientosDisponibles() - cantidadAsiento;

            if(asientosRegreso <=0){
                System.out.println("Asientos no disponibles");
                return "redirect:/usuario";
            }

            vueloDAO.modificarVuelo(vueloDeIda.getIdRuta(),new VueloDTO(vueloDeIda.getIdRuta(),vueloDeIda.getNumeroVuelo(),vueloDeIda.getFechaSalida(),vueloDeIda.getAsientosTotales(),asientosRegreso,vueloDeIda.getCostoAsiento()));
            ReservaDTO reservaRegreso = new ReservaDTO((int)idUsuario, null, ReservaEstado.Pendiente, totalPorVueloRegreso, vueloDeRegreso.getIdVuelo(), cantidadAsiento);
            reservas.add(reservaRegreso);

        }


        for (ReservaDTO reservaDTO : reservas) {
            System.out.println(reservaDAO.saveReserva(reservaDTO));
        }

        return "redirect:/usuario";
       }

    @GetMapping("/reserva-paquete/{id}")
    public String reservaPaquete(@PathVariable("id") Integer idPaquete, Model model) {
        if(idUsuario == 0){
            System.out.println("Usuario no encontrado");
            return "redirect:/usuario";
        }
        PaqueteDTO paqueteDTO = paqueteDAO.getPaquete(idPaquete);
        if (paqueteDTO != null) {
            reservaDAO.saveReserva(new ReservaDTO(
                    (int)idUsuario,
                    paqueteDTO.getIdPaquete(),
                    ReservaEstado.Pendiente,
                    paqueteDTO.getPrecio(),
                    paqueteDTO.getIdVuelo(),
                    1
            ));
            System.out.println("Reserva de paquete guardado");
            return "redirect:/usuario";
        }
        System.out.println("Reserva de paquete no encontrado");
        return "redirect:/usuario";
    }







}




