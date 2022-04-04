package com.example.bibliotecaReactiva.service;

import com.example.bibliotecaReactiva.models.Recurso;
import com.example.bibliotecaReactiva.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecursoService {

    @Autowired
    public RecursoRepository recursoRepository;


    public Mono<Recurso> agregarRecurso(Recurso recurso) {
        String  id = UUID.randomUUID().toString().substring(0,10);
        recurso.setId(id);
        return recursoRepository.save(recurso);
    }

    public Mono<Recurso> obtenerRecurso(String id) {
        return recursoRepository.findById(id);
    }

    public Flux<Recurso> obtenerTodosRecursos()
    {
        return recursoRepository.findAll();
    }

    public void eliminarRecurso(String id)
    {
        recursoRepository.deleteById(id);
    }


    public Mono<Recurso> actualizarRecurso(String id, Recurso recurso)
    {


        return recursoRepository.findById(id).flatMap(recurso1 ->
        {
            recurso.setId(id);
            return recursoRepository.save(recurso);
        });
    }

    public String prestar(String titulo)
    {

        Flux<Recurso> recursoNoDisponible = recursoRepository.findByTitulo(titulo)
                .sort(Comparator.comparing(recurso -> recurso.getFechaPrestamo()))
                .filter(recurso -> !recurso.getDisponible());

        Flux<Recurso> recursoDisponible = recursoRepository.findByTitulo(titulo)
                .filter(recurso -> recurso.getDisponible());

        Recurso ultimoPrestado = recursoNoDisponible.blockLast();

        if (recursoDisponible.blockFirst() == null) {
            return "El recurso no esta disponible, el ultimo ejemplar fue prestado en: " + ultimoPrestado.getDisponible();
        }

        return "El recurso está disponible";


    }




    public Flux<Recurso> recomendar(String tipo, String tematica)
    {
        String tipoRecurso= tipo;
        String tematicaRecurso=tematica;

        return   recursoRepository.findAllByTipoAndTematica(tipoRecurso,tematicaRecurso);
    }






}