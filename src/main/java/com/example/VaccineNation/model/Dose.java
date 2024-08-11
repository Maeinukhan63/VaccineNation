package com.example.VaccineNation.model;

import com.example.VaccineNation.Enum.VaccineBrand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Dose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String SerialNumber;

    @Enumerated(value = EnumType.STRING)
    private VaccineBrand vaccineBrand;


    @CreationTimestamp
    private Date dateOfVaccination;

    @OneToOne  //to make a relation between tables of dose to patient(1st(One -> currclass(dose)) (2nd (One -> patient class));
    @JoinColumn  //it is used to make the patientId a Foreign key in dose table
    Patient patient;
}
