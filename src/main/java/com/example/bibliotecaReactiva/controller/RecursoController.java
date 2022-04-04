package com.example.bibliotecaReactiva.controller;

import com.example.bibliotecaReactiva.models.Recurso;
import com.example.bibliotecaReactiva.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/bibliotecaReactiva")
public class RecursoController
{


    @Autowired
    RecursoService recursoService;

    @PostMapping("/recurso")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Recurso> save(@RequestBody Recurso recurso){
        return recursoService.agregarRecurso(recurso);
    }

    @GetMapping("/recurso/{id}")
    public Mono<Recurso> obtenerRecurso(@PathVariable("id")String id)
    {
        return recursoService.obtenerRecurso(id);
    }

    @GetMapping(value= "/recursos")
    public Flux<Recurso> obtenerRecursos()
    {
        return recursoService.obtenerTodosRecursos();
    }

    @DeleteMapping("/recursos/{id}")
    public void deleteRecurso(@PathVariable("id")String id)
    {
        recursoService.eliminarRecurso(id);
    }

    @PutMapping("/recurso/{id}")
    public Mono<Recurso> actualizarRecurso(@PathVariable ("id")String id, @RequestBody Recurso recurso)
    {
        return recursoService.actualizarRecurso(id,recurso);
    }

    @PutMapping("/prestar/{id}")
    public String prestar(@PathVariable ("id")String id)
    {
        return recursoService.prestar(id);
    }


    @GetMapping("/recursos/recomendacion")
    public Flux<Recurso>recomendar(@RequestParam String tipo, @RequestParam String tematica)
    {
        return  recursoService.recomendar(tipo,tematica);
    }
}
