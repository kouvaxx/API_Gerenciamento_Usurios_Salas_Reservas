package com.example.demo.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reserva {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Sala sala;

    private LocalDateTime dataHora;

    public Reserva(int id, Usuario usuario, Sala sala, LocalDateTime dataHora) {
        this.id = id;
        this.usuario = usuario;
        this.sala = sala;
        this.dataHora = dataHora;
    }
}
