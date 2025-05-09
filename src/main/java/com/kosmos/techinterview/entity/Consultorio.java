package com.kosmos.techinterview.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Consultorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false,name = "id")
    private Long id;

    private Long numberConsultorio;

    private Integer piso;

    @OneToMany(mappedBy = "consultorio")
    private List<Cita> citas;

    public Consultorio() {
    }

    public Consultorio(Long id, Long numberConsultorio, Integer piso, List<Cita> citas) {
        this.id = id;
        this.numberConsultorio = numberConsultorio;
        this.piso = piso;
        this.citas = citas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumberConsultorio() {
        return numberConsultorio;
    }

    public void setNumberConsultorio(Long numberConsultorio) {
        this.numberConsultorio = numberConsultorio;
    }

    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    @Override
    public String toString() {
        return "Consultorio{" +
                "id=" + id +
                ", numberConsultorio=" + numberConsultorio +
                ", piso=" + piso +
                ", citas=" + citas +
                '}';
    }
}

