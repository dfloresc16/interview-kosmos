package com.kosmos.techinterview.service.impl;

import com.kosmos.techinterview.dto.CitaDTO;
import com.kosmos.techinterview.dto.CitaRequestDTO;
import com.kosmos.techinterview.dto.CitaResponseDTO;
import com.kosmos.techinterview.entity.Cita;
import com.kosmos.techinterview.entity.Consultorio;
import com.kosmos.techinterview.entity.Doctor;
import com.kosmos.techinterview.mapper.GenericMapper;
import com.kosmos.techinterview.repository.CitaRepository;
import com.kosmos.techinterview.service.CitaService;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.SecondaryRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final DoctorServiceImpl doctorService;
    private final ConsultorioServiceImpl consultorioService;
    private final GenericMapper genericMapper;
    Logger log = LoggerFactory.getLogger(this.getClass());

    public CitaServiceImpl(CitaRepository citaRepository, DoctorServiceImpl doctorService, ConsultorioServiceImpl consultorioService, GenericMapper genericMapper) {
        this.citaRepository = citaRepository;
        this.doctorService = doctorService;
        this.consultorioService = consultorioService;
        this.genericMapper = genericMapper;
    }

    @Transactional
    @Override
    public CitaResponseDTO solicitarCita(CitaRequestDTO citaRequestDTO) {
        Long doctorId = citaRequestDTO.getDoctorId();
        Long consultorioId = citaRequestDTO.getConsultorioId();
        LocalDate fecha = citaRequestDTO.getFecha();
        LocalTime hora = citaRequestDTO.getHora();
        String pacienteNombre = citaRequestDTO.getPacienteNombre();

        Doctor doctor = doctorService.getDoctorById(doctorId);
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor not found with ID: " + doctorId);
        }
        Consultorio consultorio = consultorioService.getConsultorioById(consultorioId);
        if (consultorio == null) {
            throw new IllegalArgumentException("Consultorio not found with ID: " + consultorioId);
        }

        if (citaRepository.existsByConsultorioIdAndDateAndHora(consultorioId, fecha, hora)) {
            throw new IllegalStateException("Consulting room is already booked at this time.");
        }

        if (citaRepository.existsByConsultorioIdAndDateAndHora(doctorId, fecha, hora)) {
            throw new IllegalStateException("Doctor is already booked at this time.");
        }

        LocalTime twoHoursBefore = hora.minusHours(2);
        LocalTime twoHoursAfter = hora.plusHours(2);
        if (citaRepository.existsByNamePacientAndConsultorioIdAndDateAndHoraBetween(
                pacienteNombre, consultorioId, fecha, twoHoursBefore, twoHoursAfter.minusNanos(1)
        )) {
            throw new IllegalStateException("Patient already has an appointment in this consulting room within the last 2 hours.");
        }

        if (citaRepository.existsByNamePacientAndDate(pacienteNombre, fecha)) {
            throw new IllegalStateException("Patient already has an appointment scheduled for today.");
        }

        LocalTime threePm = LocalTime.of(15, 0);
        List<Cita> doctorAppointmentsOnDate = citaRepository.findByDoctorIdAndDate(doctorId, fecha);
        long appointmentsBefore3pm = doctorAppointmentsOnDate.stream()
                .filter(cita -> cita.getHour().isBefore(threePm))
                .count();
        long appointmentsAfter3pm = doctorAppointmentsOnDate.stream()
                .filter(cita -> cita.getHour().isAfter(threePm) || cita.getHour().equals(threePm))
                .count();

        if (hora.isBefore(threePm) && appointmentsBefore3pm >= 2) {
            throw new IllegalStateException("Doctor has already reached the maximum of 2 appointments before 3 pm.");
        }

        if ((hora.isAfter(threePm) || hora.equals(threePm)) && appointmentsAfter3pm >= 1) {
            throw new IllegalStateException("Doctor has already reached the maximum of 1 appointment after 3 pm.");
        }

        Cita cita = new Cita();
        cita.setDate(fecha);
        cita.setHour(hora);
        cita.setNamePacient(pacienteNombre);
        cita.setDoctor(doctor);
        cita.setConsultorio(consultorio);
        cita.setEstado("AGENDADA");
        log.info("Cita: {}", cita);

        Cita savedCita = citaRepository.save(cita);
        CitaResponseDTO citaResponseDTO = genericMapper.toDto(savedCita, CitaResponseDTO.class);
        return citaResponseDTO;
    }

    public List<Cita> getCitasByDoctorAndFecha(Long doctorId, LocalDate fecha) {
        return citaRepository.findByDoctorIdAndDate(doctorId, fecha).stream()
                .toList();
    }

    public List<Cita> getCitasByConsultorioAndFecha(Long consultorioId, LocalDate fecha) {
        return citaRepository.findByConsultorioIdAndDate(consultorioId, fecha).stream()
                .toList();
    }

    @Transactional
    public CitaDTO cancelarCita(Long citaId) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + citaId));

        LocalDateTime appointmentDateTime = LocalDateTime.of(cita.getDate(), cita.getHour());
        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Mexico_City"));
        Duration timeUntilAppointment = Duration.between(now, appointmentDateTime);

        if (timeUntilAppointment.toHours() < 24) {
            cita.setEstado("CANCELADA");
            Cita updatedCita = citaRepository.save(cita);
            return convertToDto(updatedCita);
        } else {
            throw new IllegalStateException("Appointment cannot be canceled as it is less than 24 hours away.");
        }
    }

    private CitaDTO convertToDto(Cita cita) {
        return new CitaDTO(
                cita.getId(),
                cita.getDate(),
                cita.getHour(),
                cita.getNamePacient(),
                cita.getDoctor().getId(),
                cita.getConsultorio().getId(),
                cita.getEstado()
        );
    }

}
