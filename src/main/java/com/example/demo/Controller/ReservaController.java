package com.example.demo.Controller;

import com.example.demo.Entity.Reserva;
import com.example.demo.Service.ReservaService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("reservas")
public class ReservaController {

@Autowired
private ReservaService reservaService;

@PostMapping
    public ResponseEntity<Reserva> criarReserva(@RequestBody Reserva reserva) {
    Reserva novaReserva = reserva.Service.criarReserva(reserva);
    return ResponseEntity.ok(novaReserva);
}

@GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarReserva(@PathVariable Integer id) {
      Reserva reserva = reservaService.buscarReservaPorId(Id);
      return ResponseEntity.ok(reserva);
}

@PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizarReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
     Reserva reservaAtualizada = reservaService.atualizarReserva(id, reserva);
     return ResponseEntity.ok(reservaAtualizada);
}
    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<Reserva> deletarReserva(@PathVariable Integer usuarioId) {
    return ResponseEntity.noContent().build();
    }

@GetMapping("/usuario/{usuarioId}")
public ResponseEntity<List<Reserva>> getReservasPorUsuario @PathVariable Long usuarioId) {
        List<Reserva> reservas = reservaService.listarReservasPorUsuario(usuarioId);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Reserva>> getReservasPorUsuarioId(@PathVariable Long usuarioId) {
    return ResponseEntity.ok(reservas);
    }

@GetMapping("/sala/{salaId}")
    public ResponseEntity<List<Reserva>> getReservasPorSala(@PathVariable Long salaId) {
    return ResponseEntity.ok(reservas);
}

}


