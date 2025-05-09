package com.kosmos.techinterview.entity;

import jakarta.persistence.*;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false,name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "secondlastname")
    private String secondLastName;
    @Column(name = "specialty")
    private String specialty;

    public Doctor() {
    }

    public Doctor(Long id, String name, String lastName, String secondLastName, String specialty) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.specialty = specialty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
