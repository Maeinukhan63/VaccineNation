package com.example.VaccineNation.controller;


import com.example.VaccineNation.model.Doctor;
import com.example.VaccineNation.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorservice;

    @PostMapping("/add")
    public String addDoctor(@RequestBody Doctor doctor){
       return doctorservice.addDoctor(doctor);
    }

    @GetMapping("/get")
    public Doctor getDoctor(@RequestParam("id") int Id){
       return doctorservice.getDoctor(Id);
    }

    @DeleteMapping("/delete")
    public String deleteDocotr(@RequestParam("id") int Id){
        return doctorservice.deleteDoctor(Id);
    }
}
