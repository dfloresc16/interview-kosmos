package com.kosmos.techinterview.controller;

import com.kosmos.techinterview.entity.Consultorio;
import com.kosmos.techinterview.service.impl.ConsultorioServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultorios")
public class ConsultorioController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private final ConsultorioServiceImpl consultorioService;

    public ConsultorioController(ConsultorioServiceImpl consultorioService) {
        this.consultorioService = consultorioService;
    }

    @PostMapping
    public ResponseEntity<Consultorio> registerConsultorio(@RequestBody Consultorio consultorio) {
        log.info("Registrando consultorio: {}", consultorio);
        Consultorio savedConsultorio = consultorioService.createConsultorio(consultorio);
        log.info("Registrando consultorio: {}", savedConsultorio);
        return new ResponseEntity<>(savedConsultorio, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultorio> getConsultorioById(@PathVariable Long id) {
        log.info("Retornando consultorio: {}", id);
        Consultorio consultorio = consultorioService.getConsultorioById(id);
        log.info("Retornando consultorio: {}", consultorio);
        if (consultorio != null) {
            log.info("Retornando consultorio: {}", consultorio);
            return new ResponseEntity<>(consultorio, HttpStatus.OK);
        } else {
            log.info("Retornando consultorio: {}", consultorio);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Consultorio>> getAllConsultorios() {
        log.info("Retornando consultorios");
        List<Consultorio> consultorios = consultorioService.getAllConsultorios();
        log.info("Retornando consultorios {}", consultorios);
        return new ResponseEntity<>(consultorios, HttpStatus.OK);
    }
}