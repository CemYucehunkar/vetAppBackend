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
// AppointmentController sınıfı oluşturuldu ve bu sınıfın bir örneği oluşturuldu. Bu sınıf, AppointmentService sınıfı ile işlem yapar.
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<> (appointmentService.getAllResponses (), HttpStatus.OK);
    }
// getAll metodu oluşturuldu ve bu metod, appointmentService sınıfının getAllResponses metodunu çağırır. Bu metod, tüm randevuların bilgilerini döndürür.
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<> (appointmentService.getResponseById (id), HttpStatus.OK);
    }
// getById metodu oluşturuldu ve bu metod, appointmentService sınıfının getResponseById metodunu çağırır. Bu metod, belirli bir randevunun bilgilerini döndürür.
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
// getByDoctorNameStartAndFinishDate metodu oluşturuldu ve bu metod, appointmentService sınıfının getByDoctorNameStartAndFinishDate metodunu çağırır. Bu metod, belirli bir doktora ait olan randevuların bilgilerini döndürür.
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
// getByAnimalNameStartAndFinishDate metodu oluşturuldu ve bu metod, appointmentService sınıfının getByAnimalNameStartAndFinishDate metodunu çağırır. Bu metod, belirli bir hayvana ait olan randevuların bilgilerini döndürür.
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
// update metodu oluşturuldu ve bu metod, appointmentService sınıfının update metodunu çağırır. Bu metod, belirli bir randevunun bilgilerini günceller.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        appointmentService.deleteById (id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }
}