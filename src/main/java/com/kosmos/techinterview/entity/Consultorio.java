package com.kosmos.techinterview.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Consultorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numberConsultorio;

    private Integer piso;

    @OneToMany(mappedBy = "consultorio") // One consulting room can have many appointments
    private Listq<Cita> citas;
}
