package com.example.VaccineNation.service;

import com.example.VaccineNation.Enum.Gender;
import com.example.VaccineNation.dto.request.PatientRequest;
import com.example.VaccineNation.dto.response.PatientResponse;
import com.example.VaccineNation.exception.patientNotFound;
import com.example.VaccineNation.model.Patient;
import com.example.VaccineNation.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public PatientResponse addPatient(PatientRequest patientRequest) {

        //1. convert dto-> model entity
        Patient patient=new Patient();
        patient.setVaccinated(false);
        patient.setName(patientRequest.getName());
        patient.setAge(patientRequest.getAge());
        patient.setGender(patientRequest.getGender());
        patient.setEmailId(patientRequest.getEmailId());

         Patient savedpatient= patientRepository.save(patient); //return saved Patient in DB

        //2. change patient model into PatientResponse

        PatientResponse patientResponse=new PatientResponse();

        patientResponse.setName(savedpatient.getName());
        patientResponse.setVaccinated(savedpatient.isVaccinated());
        patientResponse.setEmailId(savedpatient.getEmailId());

        return patientResponse;
    }

    public PatientResponse getPatient(int id) {
        Optional<Patient> patientOptional=patientRepository.findById(id);//it search obj in base of primary key

        if(patientOptional.isEmpty()){
            throw new patientNotFound("Invalid patient id");
        }
        Patient patient= patientOptional.get();

        PatientResponse patientResponse=new PatientResponse();

        patientResponse.setName(patient.getName());
        patientResponse.setVaccinated(patient.isVaccinated());
        patientResponse.setEmailId(patient.getEmailId());

        return patientResponse;
    }

    public List<PatientResponse> getAllMalePatientByGender(Gender gender) {
        List<Patient> patients=patientRepository.findAll();

        List<PatientResponse> patientResponses=new ArrayList<>();

        for(Patient patient:patients){
            if(patient.getGender()==gender){
                PatientResponse patientResponse=new PatientResponse();

                patientResponse.setName(patient.getName());
                patientResponse.setVaccinated(patient.isVaccinated());
                patientResponse.setEmailId(patient.getEmailId());

                patientResponses.add(patientResponse);
            }
        }
        return patientResponses;
    }

    public List<PatientResponse> getAllVaccinatedPatient(int age) {
        List<Patient> patients=patientRepository.findAll();

        List<PatientResponse> patientResponses=new ArrayList<>();

        for(Patient patient:patients){
            if(patient.isVaccinated()==true && age < patient.getAge()){
                PatientResponse patientResponse=new PatientResponse();

                patientResponse.setName(patient.getName());
                patientResponse.setVaccinated(patient.isVaccinated());
                patientResponse.setEmailId(patient.getEmailId());

                patientResponses.add(patientResponse);
            }
        }
        return patientResponses;
    }

    public List<PatientResponse> getAllUnVacinatedPatient(boolean vaccinated) {
        List<Patient> patients= patientRepository.findAll();

        List<PatientResponse> patientResponses = new ArrayList<>();

        for(Patient patient:patients){
            if(patient.isVaccinated()==vaccinated){
                PatientResponse patientResponse=new PatientResponse();

                patientResponse.setEmailId(patient.getEmailId());
                patientResponse.setVaccinated(patient.isVaccinated());
                patientResponse.setName(patient.getName());

                patientResponses.add(patientResponse);

            }
        }
        return patientResponses;
    }

    public List<PatientResponse> changeVacciStatusForAllPatient(boolean vaccinated) {
        List<Patient> patients =patientRepository.findAll();

        List<PatientResponse> patientResponses=new ArrayList<>();

        for(Patient patient:patients){
            if(patient.isVaccinated()==vaccinated ){
                PatientResponse patientResponse=new PatientResponse();

                patientResponse.setName(patient.getName());
                patientResponse.setEmailId(patient.getEmailId());
                patientResponse.setVaccinated(!vaccinated);

                patientResponses.add(patientResponse);
            }else{
                PatientResponse patientResponse =new PatientResponse();

                patientResponse.setName(patient.getName());
                patientResponse.setEmailId(patient.getEmailId());
                patientResponse.setVaccinated(vaccinated);

                patientResponses.add(patientResponse);
            }
        }
        return patientResponses;
    }

    public String DeleteParticularPatient(int id) {
        patientRepository.deleteById(id);
        return "Patient Details Deleted";
    }
}
