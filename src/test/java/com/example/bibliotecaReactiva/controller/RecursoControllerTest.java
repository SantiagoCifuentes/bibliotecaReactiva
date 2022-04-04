package com.example.bibliotecaReactiva.controller;

import com.example.bibliotecaReactiva.models.Recurso;
import com.example.bibliotecaReactiva.service.RecursoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecursoControllerTest
{

    @Autowired
    WebTestClient webClient;

    @Autowired
    RecursoService recursoService;

    @Test
    @DisplayName("Trae todas los recursos")
    void obtenerRecursos() throws Exception {
        webClient.get().uri("/bibliotecaReactiva/recursos")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_VALUE)
                .expectBodyList(Recurso.class);
    }

    @Test
    void genererRecurso()
    {
        Recurso recurso = new Recurso();

        recurso.setId("1");
        recurso.setDisponible(true);
        recurso.setFechaPrestamo(LocalDateTime.now());
        recurso.setTematica("ciencias");
        recurso.setTipo("libro");
        recurso.setTitulo("El hombre y sus simbolos");

        Mono<Recurso> recursoMono = recursoService.agregarRecurso(recurso);
        StepVerifier.create(recursoMono).expectNext(recurso).verifyComplete();

    }

}