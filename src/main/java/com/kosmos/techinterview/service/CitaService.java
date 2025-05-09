package com.kosmos.techinterview.service;

import com.kosmos.techinterview.dto.CitaRequestDTO;
import com.kosmos.techinterview.dto.CitaResponseDTO;

public interface CitaService {

    CitaResponseDTO solicitarCita(CitaRequestDTO citaRequestDTO);
}
