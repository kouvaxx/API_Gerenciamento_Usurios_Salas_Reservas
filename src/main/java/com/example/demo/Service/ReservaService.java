package com.example.demo.Service;

import com.example.demo.Entity.Reserva;
import com.example.demo.Entity.Sala;
import com.example.demo.Entity.Usuario;
import com.example.demo.Repository.ReservaRepository;
import com.example.demo.Repository.SalaRepository;
import com.example.demo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SalaRepository salaRepository;


    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public  Reserva createReserva(Reserva reserva, Long IdSala, Long IdUsuario) {
        //Checar usuario;
        Optional<Usuario> byIdUsuario = usuarioRepository.findById(IdUsuario);
        if (byIdUsuario.isEmpty()) {
            return null;
        }
        reserva.setUsuario(byIdUsuario.get());


        //Checar Sala;
        Optional<Sala> byIdSala = salaRepository.findById(IdSala);
        if (byIdSala.isEmpty()) {
            return null;
        }
        reserva.setSala(byIdSala.get());

        //Data já passou;
        LocalDateTime now = LocalDateTime.now();
        if (reserva.getData_reserva().isBefore(now)) {
            return null;
        }

        //Antecedencia de 30 dias;
        Long dias = ChronoUnit.DAYS.between(reserva.getData_pedido(), reserva.getData_reserva());
        if (dias > 30) {
            return null;
        }

        //Outro usuario reservou;
        Optional<Integer> firstByDataPedido = reservaRepository.findFirstByData_pedido(reserva.getSala().getId_sala(), reserva.getData_reserva());
        if (firstByDataPedido.isPresent()) {
            return null;
        }

        //Usuario possui reserva mesmo dia;
        Optional<Integer> firstByidUsuario = reservaRepository.findFirstByidUsuario(reserva.getUsuario().getId_usuario(), reserva.getData_reserva());
        if (firstByidUsuario.isPresent()) {
            return null;
        }

        return reservaRepository.save(reserva);

    }

    public Optional<Reserva> findById(Long id) {
        return reservaRepository.findById(id);
    }

    public Optional<Reserva> deleteById (Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva atualizarReserva (Reserva reservaNova, Long idSala, Long idUsuario) {
        //checar usuario existe
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if(usuario.isEmpty()){
            return null;
        }
        reservaNova.setUsuario(usuario.get());

        //checar sala existe e esta ativa
        Optional<Sala> byIdSala = salaRepository.findById_sala(idSala);
        if(byIdSala.isEmpty()){
            return null;
        }
        reservaNova.setSala(byIdSala.get());

        //checar se a data ja passou
        LocalDateTime localDateTime  = LocalDateTime.now();
        if(reservaNova.getData_reserva().isBefore(localDateTime)) {
            return null;
        }

        //checar 30 dias de antecendia
        long diasEntre = ChronoUnit.DAYS.between(reservaNova.getData_pedido(), reservaNova.getData_reserva());
        if(diasEntre > 30){
            return null;
        }

        Optional<Reserva> byId = reservaRepository.findById(reservaNova.getId_reserva());
        if(byId.isPresent()){

            if(byId.get().getUsuario().getId_usuario() != idUsuario){
                //checar se outro usuario ja reservou a sala
                Optional<Integer> byDataPedido = reservaRepository.findFirstByData_pedido(idSala, reservaNova.getData_reserva());
                if(!byDataPedido.isPresent()){
                    return null;
                }

                //checar se usuario ja possui uma reserva no mesmo horario
                Optional<Integer> byusuarioId = reservaRepository.findFirstByidUsuario(idUsuario, reservaNova.getData_reserva());
                if(!byusuarioId.isPresent()){
                    return null;
                }
            }

        }

        return reservaRepository.save(reservaNova);
    }

    public List<Reserva> usuarioReservas(Long userID){

        return reservaRepository.findByidUsuario(userID);
    }

    public List<Reserva> salaReservas(Long salaID){

        return reservaRepository.findByidSala(salaID);
    }


}

