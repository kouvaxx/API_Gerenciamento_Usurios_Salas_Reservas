package com.example.demo.Service;

import com.example.demo.Entity.Reserva;
import com.example.demo.Entity.Sala;
import com.example.demo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SalaRepository salaRepository;

    public Reserva criarReserva(Reserva reserva) {
        Sala sala = salaRepository.findById(reserva.getSala().getId()).orElseThrow(() -> new RuntimeException("Sala não encontrada"));

        if (!sala.isAtiva()) {
            throw new RuntimeException("Sala inativa, não pode ser reservada.");
        }

        if (reserva.getDataHora().isAfter(LocalDateTime.now()).plusDays(30))
            throw new RuntimeException("Reservas só podem ser feitas com até 30 dias de antecedência.");
    }

    return public ReservaRepository.save(reservas);

    }

