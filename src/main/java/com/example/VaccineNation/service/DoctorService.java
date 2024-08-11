package com.example.VaccineNation.service;

import com.example.VaccineNation.exception.DoctorNotFoundException;
import com.example.VaccineNation.model.Doctor;
import com.example.VaccineNation.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public String addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
        return "Doctor info Saved";
    }

    public Doctor getDoctor(int id) {
        Optional<Doctor> doctorOptional= doctorRepository.findById(id);

        if(doctorOptional.isEmpty()){
            throw new DoctorNotFoundException("Invalid Doctor Details");
        }

        Doctor doctor=doctorOptional.get();
        return doctor;
    }

    public String deleteDoctor(int id) {
        doctorRepository.deleteById(id);
        return "Doctor Details has been deleted";
    }
}
