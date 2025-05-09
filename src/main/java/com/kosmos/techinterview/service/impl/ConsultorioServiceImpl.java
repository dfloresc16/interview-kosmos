package com.kosmos.techinterview.service.impl;

import com.kosmos.techinterview.entity.Consultorio;
import com.kosmos.techinterview.repository.ConsultorioRepository;
import com.kosmos.techinterview.service.ConsultorioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultorioServiceImpl implements ConsultorioService {
    private final ConsultorioRepository consultorioRepository;

    public ConsultorioServiceImpl(ConsultorioRepository consultorioRepository) {
        this.consultorioRepository = consultorioRepository;
    }

    public Consultorio getConsultorioById(Long id) {
        return consultorioRepository.findById(id)
                .orElse(null); // Or throw an exception if you prefer
    }

    @Override
    public Consultorio createConsultorio(Consultorio consultorio) {
        return consultorioRepository.save(consultorio);
    }

    @Override
    public List<Consultorio> getAllConsultorios() {
        return consultorioRepository.findAll();
    }
}
