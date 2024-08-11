package com.example.VaccineNation.exception;

public class patientNotFound extends RuntimeException{
    public patientNotFound(String message){
        super(message);
    }
}
