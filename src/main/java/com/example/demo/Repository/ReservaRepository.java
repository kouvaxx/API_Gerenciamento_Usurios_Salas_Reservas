package com.example.demo.Repository;

import com.example.demo.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT id_reserva FROM Reserva WHERE sala.id_sala = :id AND data_reserva = :data AND status = true ")
    Optional<Integer> findFirstByData_pedido(Long id, LocalDateTime data);


    @Query("SELECT id_reserva FROM Reserva WHERE usuario.id_usuario = :id AND data_reserva = :data AND status = true ")
    Optional<Integer> findFirstByidUsuario(Long id, LocalDateTime data);

    @Query("SELECT r FROM Reserva r WHERE usuario.id_usuario = :id")
    List<Reserva> findByidUsuario(Long id);

    @Query("SELECT r FROM Reserva r WHERE r.sala.id_sala = :id")
    List<Reserva> findByidSala(Long id);



}
