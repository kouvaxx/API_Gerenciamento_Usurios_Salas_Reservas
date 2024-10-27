package com.example.demo.Repository;

import com.example.demo.Entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SalaRepository extends JpaRepository<Sala, Long> {

    @Query
    ("SELECT s FROM Sala s WHERE s.id_sala = :id AND s.status = true")
    Optional<Sala> findById_sala(Long id);
}
