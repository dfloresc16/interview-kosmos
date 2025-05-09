package com.kosmos.techinterview.service;

import com.kosmos.techinterview.entity.Doctor;

import java.util.List;


public interface DoctorService {

    Doctor getDoctorById(Long id);

    Doctor createDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();
}
