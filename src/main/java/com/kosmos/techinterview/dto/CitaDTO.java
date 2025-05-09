package com.kosmos.techinterview.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaDTO {

    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private String pacienteNombre;
    private Long doctorId;
    private Long consultorioId;
    private String estado;

    public CitaDTO() {
    }

    public CitaDTO(Long id, LocalDate fecha, LocalTime hora, String pacienteNombre, Long doctorId, Long consultorioId, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.pacienteNombre = pacienteNombre;
        this.doctorId = doctorId;
        this.consultorioId = consultorioId;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getConsultorioId() {
        return consultorioId;
    }

    public void setConsultorioId(Long consultorioId) {
        this.consultorioId = consultorioId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
