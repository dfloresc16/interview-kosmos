package com.kosmos.techinterview.controller;

import com.kosmos.techinterview.entity.Doctor;
import com.kosmos.techinterview.service.impl.DoctorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private final DoctorServiceImpl doctorService;

    public DoctorController(DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<Doctor> registerDoctor(@RequestBody Doctor doctor) {
        log.info("Registering Doctor: {}", doctor);
        Doctor savedDoctor = doctorService.createDoctor(doctor);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        log.info("Getting doctor by id: {}", id);
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            log.info("Found doctor: {}", doctor);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            log.info("No doctor found with id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        log.info("Found {} doctors", doctors.size());
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
}
