package com.kosmos.techinterview.service;

import com.kosmos.techinterview.entity.Consultorio;

import java.util.List;

public interface ConsultorioService {
    Consultorio getConsultorioById(Long id);

    Consultorio createConsultorio(Consultorio consultorio);

    List<Consultorio> getAllConsultorios();
}
