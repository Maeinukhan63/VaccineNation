package com.example.VaccineNation.service;

import com.example.VaccineNation.Enum.AppointmentStatus;
import com.example.VaccineNation.dto.response.AppointmentResponse;
import com.example.VaccineNation.dto.response.PatientResponse;
import com.example.VaccineNation.exception.DoctorNotFoundException;
import com.example.VaccineNation.exception.patientNotFound;
import com.example.VaccineNation.model.Appointment;
import com.example.VaccineNation.model.Doctor;
import com.example.VaccineNation.model.Patient;
import com.example.VaccineNation.repository.AppointmentRepository;
import com.example.VaccineNation.repository.DoctorRepository;
import com.example.VaccineNation.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    public AppointmentResponse addAppointment(int patientId, int doctorId) {
        Optional<Patient> patientOptional= patientRepository.findById(patientId);

        if(patientOptional.isEmpty()){
            throw new patientNotFound("Invalid patient id");
        }

        Optional<Doctor> doctorOptional=doctorRepository.findById(doctorId);
        if(doctorOptional.isEmpty()){
            throw new DoctorNotFoundException("Invalid Doctor id");
        }

        Patient patient=patientOptional.get();
        Doctor doctor=doctorOptional.get();

        //add an appointment
        Appointment appointment=new Appointment();
        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));
        appointment.setStatus(AppointmentStatus.BOOKED);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        Appointment savedappointment= appointmentRepository.save(appointment);

        AppointmentResponse appointmentResponse=new AppointmentResponse();
        appointmentResponse.setAppointmentId(savedappointment.getAppointmentId());
        appointmentResponse.setStatus(savedappointment.getStatus());
        appointmentResponse.setDateOfAppointmtnt(savedappointment.getDateOfAppointmtnt());
        appointmentResponse.setDoctorName(savedappointment.getDoctor().getName());

        Patient savedPatient = savedappointment.getPatient();

        PatientResponse patientResponse =new PatientResponse();

        patientResponse.setName(savedPatient.getName());
        patientResponse.setVaccinated(savedPatient.isVaccinated());
        patientResponse.setEmailId(savedPatient.getEmailId());

        appointmentResponse.setPatientResponse(patientResponse);

        return appointmentResponse;
    }
}
