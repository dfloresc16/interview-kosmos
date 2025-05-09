package com.kosmos.techinterview.repository;

import com.kosmos.techinterview.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    boolean existsByConsultorioIdAndDateAndHora(Long consultorioId,LocalDate date, LocalTime Hora);
    
    boolean existsByNamePacientAndConsultorioIdAndDateAndHoraBetween(String namePacient, Long consultorioId,
                                                                     LocalDate date, LocalTime twoHorasBefore, LocalTime twoHorasAfter);
    boolean existsByNamePacientAndDate(String namePacient, LocalDate date);

    List<Cita> findByDoctorIdAndDate(Long doctorId, LocalDate date);

    List<Cita> findByConsultorioIdAndDate(Long consultorioId, LocalDate date);
}
