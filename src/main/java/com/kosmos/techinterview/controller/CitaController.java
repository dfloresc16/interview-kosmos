package com.kosmos.techinterview.controller;


import com.kosmos.techinterview.dto.CitaDTO;
import com.kosmos.techinterview.dto.CitaRequestDTO;
import com.kosmos.techinterview.dto.CitaResponseDTO;
import com.kosmos.techinterview.entity.Cita;
import com.kosmos.techinterview.service.impl.CitaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaServiceImpl citaService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    public CitaController(CitaServiceImpl citaService) {
        this.citaService = citaService;
    }

    @PostMapping("/solicitud")
    public ResponseEntity<CitaResponseDTO> solicitarCita(@RequestBody CitaRequestDTO request) {
        try {
            log.info("Solicitando cita " + request);
            CitaResponseDTO response = citaService.solicitarCita(request);
            log.info("Cita solicitada " + response);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IllegalStateException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/doctor/{doctorId}/fecha/{fecha}")
    public ResponseEntity<List<Cita>> getDoctorCitasByDate(@PathVariable Long doctorId, @PathVariable LocalDate fecha) {
        log.info("Buscando citas para " + doctorId + " e " + fecha);
        List<Cita> citas = citaService.getCitasByDoctorAndFecha(doctorId, fecha);
        log.info("Citas " + citas);
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

    @GetMapping("/consultorio/{consultorioId}/fecha/{fecha}")
    public ResponseEntity<List<Cita>> getConsultorioCitasByDate(@PathVariable Long consultorioId, @PathVariable LocalDate fecha) {
        List<Cita> citas = citaService.getCitasByConsultorioAndFecha(consultorioId, fecha);
        log.info("Citas " + citas);
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<CitaDTO> cancelarCita(@PathVariable Long id) {
        try {
            log.info("Cancelar cita " + id);
            CitaDTO response = citaService.cancelarCita(id);
            log.info("Cita cancelada " + response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}