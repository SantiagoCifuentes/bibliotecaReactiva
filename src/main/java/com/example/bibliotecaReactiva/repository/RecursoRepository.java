package com.example.bibliotecaReactiva.repository;

import com.example.bibliotecaReactiva.models.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;


@Repository
public interface RecursoRepository extends ReactiveMongoRepository <Recurso,String>
{
    Flux<Recurso> findByTitulo(String tipo);
    Flux<Recurso> findAllByTipoAndTematica(String tipo, String tematica);

}
