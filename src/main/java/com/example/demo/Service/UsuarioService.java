package com.example.demo.Service;

import com.example.demo.Entity.Usuario;
import com.example.demo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository repository;

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario saveUsuario(Usuario usuario){
        return repository.save(usuario);
    }

    public Optional<Usuario> findById(long ID){
        return repository.findById(ID);
    }

    public Optional<Usuario> deleteById(long ID){
        Optional<Usuario> usuario = repository.findById(ID);

        if(usuario.isPresent()){
            repository.deleteById(ID);
        }

        return usuario;
    }

    public Optional<Usuario> atualizarUsuario(Long id, Usuario usuarioNovo){

        Optional<Usuario> byId = repository.findById(id);

        if(byId.isEmpty()){
            return byId;
        }

        return Optional.of(repository.save(usuarioNovo));
    }

}
