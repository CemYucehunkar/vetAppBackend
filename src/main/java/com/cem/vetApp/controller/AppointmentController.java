package com.cem.vetApp.controller;

import com.cem.vetApp.dto.request.AppointmentRequest;
import com.cem.vetApp.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.cem.vetApp.config.BaseURL.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<> (appointmentService.getAllResponses (), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<> (appointmentService.getResponseById (id), HttpStatus.OK);
    }

    @GetMapping("/doctor-name/{doctorName}/start-date/{startDate}/finish-date/{finishDate}")
    public ResponseEntity<?> getByDoctorNameStartAndFinishDate(
            @PathVariable("doctorName") String doctorName,
            @PathVariable("startDate") LocalDate startDate,
            @PathVariable("finishDate") LocalDate finishDate
    ) {
        return new ResponseEntity<> (
                appointmentService.getByDoctorNameStartAndFinishDate (doctorName, startDate, finishDate),
                HttpStatus.OK
        );
    }

    @GetMapping("/animal-name/{animalName}/start-date/{startDate}/finish-date/{finishDate}")
    public ResponseEntity<?> getByAnimalNameStartAndFinishDate(
            @PathVariable("animalName") String animalName,
            @PathVariable("startDate") LocalDate startDate,
            @PathVariable("finishDate") LocalDate finishDate
    ) {
        return new ResponseEntity<> (
                appointmentService.getByAnimalNameStartAndFinishDate (animalName, startDate, finishDate),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        return new ResponseEntity<> (appointmentService.create (appointmentRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody AppointmentRequest appointmentRequest
    ) {
        return new ResponseEntity<> (appointmentService.update (id, appointmentRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        appointmentService.deleteById (id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }
}