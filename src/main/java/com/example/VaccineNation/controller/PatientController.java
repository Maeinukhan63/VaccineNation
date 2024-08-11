package com.example.VaccineNation.controller;

import com.example.VaccineNation.Enum.Gender;
import com.example.VaccineNation.dto.request.PatientRequest;
import com.example.VaccineNation.dto.response.PatientResponse;
import com.example.VaccineNation.model.Patient;
import com.example.VaccineNation.service.PatientService;
import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/add")
    public ResponseEntity addPatient(@RequestBody PatientRequest patientRequest){
         try {
             PatientResponse patientResponse=patientService.addPatient(patientRequest);
             return new ResponseEntity(patientResponse, HttpStatus.CREATED);

         }
         catch(Exception e){
             return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
         }
    }

    @GetMapping("/get")
    public PatientResponse getPatient(@RequestParam("id") int id){

        return patientService.getPatient(id);
    }

    //get all patient of a particular gender
    @GetMapping("/get/gender/{gender}")
    public List<PatientResponse> getAllMalePatientByGender(@PathVariable("gender") Gender gender){
         return patientService.getAllMalePatientByGender(gender);
    }

    //get all the vaccinated patients above age 30
    @GetMapping("get/VaccPatient/{age}")
    public List<PatientResponse> getAllVaccinatedPatient(@PathVariable("age") int age){
        return patientService.getAllVaccinatedPatient(age);
    }


    //get all unvaccinates patient MALE/FEMALE
    @GetMapping("/get/unVacci/{vacci}")
    public List<PatientResponse> getAllUnVacinatedPatient(@PathVariable("vacci") boolean vaccinated){
        return patientService.getAllUnVacinatedPatient(vaccinated);
    }

    //change the vaccinated status for all the patient 1-> 0 / 0->1
    @PutMapping("/update/VacciStatus/{isVacci}")
    public List<PatientResponse> changeVacciStatusForAllPatient(@PathVariable("isVacci") boolean vaccinated){
        return patientService.changeVacciStatusForAllPatient(vaccinated);
    }

    @DeleteMapping("/delete")
    public String DeleteParticularPatient(@RequestParam("id") int id){
        return patientService.DeleteParticularPatient(id);
    }
}
