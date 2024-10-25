package com.example.demo.Service;

import com.example.demo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository repository;

    public List<UsuarioService> findAll(){
        return repository.findAll();
    }

    public UsuarioService saveUsuario(UsuarioService usuarioService){
        return repository.save(usuarioService);
    }

    public Optional<UsuarioService> findById(long ID){
        return repository.findById(ID);
    }

    public Optional<UsuarioService> deleteById(long ID){
        Optional<UsuarioService> usuario = repository.findById(ID);

        if(usuario.isPresent()){
            repository.deleteById(ID);
        }

        return usuario;
    }

    public Optional<UsuarioService> atualizarUsuario(Long id, UsuarioService usuarioServiceNovo){

        Optional<UsuarioService> byId = repository.findById(id);

        if(byId.isEmpty()){
            return byId;
        }

        return Optional.of(repository.save(usuarioServiceNovo));
    }

}
