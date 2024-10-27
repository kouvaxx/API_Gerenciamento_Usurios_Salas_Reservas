package com.example.demo.Repository;

import com.example.demo.Entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;


@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
