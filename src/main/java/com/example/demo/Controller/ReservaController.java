package com.example.demo.Controller;

import com.example.demo.DTOs.ReservaDTO;
import com.example.demo.Entity.Reserva;
import com.example.demo.Service.ReservaService;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<Reserva>> findAll() {

        return ResponseEntity.status(HttpStatus.OK).body(reservaService.findAll());

    }

    @PostMapping
    public ResponseEntity<Object> createReserva(@RequestBody @Valid ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva();
        BeanUtils.copyProperties(reservaDTO, reserva);
        reserva.setStatus(reservaDTO.status() == 1);


        Reserva retornoReserva = reservaService.createReserva(reserva, reservaDTO.sala_id(), reservaDTO.usuario_id());
        if (retornoReserva == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Voce descumpriu alguma regra da reserva!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(retornoReserva);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Reserva> byId = reservaService.findById(id);

        if (byId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sala não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(byId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<Reserva> byId = reservaService.deleteById(id);
        if (byId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sala não encontrado");
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(byId);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva();
        BeanUtils.copyProperties(reservaDTO, reserva);
        reserva.setStatus(reservaDTO.status() == 1);
        reserva.setId_reserva(id);

       Reserva reserva1 = reservaService.atualizarReserva(reserva, reservaDTO.sala_id(), reservaDTO.usuario_id());

        if (reserva1 == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Voce descumpriu alguma regra de atualizar reserva!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(reserva1);
    }
    @GetMapping("/{userId}/usuario")
    private ResponseEntity<Object> usuarioReservas(@PathVariable Long userId){

        List<Reserva> reservasUsuario = reservaService.usuarioReservas(userId);

        if(reservasUsuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não possui reservas");
        }
        return ResponseEntity.status(HttpStatus.OK).body(reservasUsuario);
    }

    @GetMapping("/{salaId}/salas")
    private ResponseEntity<Object> salaReservas(@PathVariable Long salaId){

        List<Reserva> reservasSala = reservaService.salaReservas(salaId);

        if(reservasSala.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não possui reservas");
        }
        return ResponseEntity.status(HttpStatus.OK).body(reservasSala);
    }

}


