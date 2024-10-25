package com.example.demo.Repository;

import com.example.demo.Service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;


@Transactional
public interface UsuarioRepository extends JpaRepository<UsuarioService, Long> {
}
