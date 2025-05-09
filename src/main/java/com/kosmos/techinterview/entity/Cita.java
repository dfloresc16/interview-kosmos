package com.kosmos.techinterview.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private LocalTime hora;

    private String namePacient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId", nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultorioId", nullable = false)
    private Consultorio consultorio;

    @Column(nullable = false)
    private String estado;

    public Cita() {
    }

    public Cita(Long id, LocalDate date, LocalTime hour, String namePacient, Doctor doctor, Consultorio consultorio, String estado) {
        this.id = id;
        this.date = date;
        this.hora = hour;
        this.namePacient = namePacient;
        this.doctor = doctor;
        this.consultorio = consultorio;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hora;
    }

    public void setHour(LocalTime hour) {
        this.hora = hour;
    }

    public String getNamePacient() {
        return namePacient;
    }

    public void setNamePacient(String namePacient) {
        this.namePacient = namePacient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
