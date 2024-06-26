package com.cem.vetApp.controller;

import com.cem.vetApp.dto.request.DoctorRequest;
import com.cem.vetApp.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cem.vetApp.config.BaseURL.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
// Burada DoctorController sınıfı oluşturuldu ve bu sınıfın bir örneği oluşturuldu. Bu sınıf, DoctorService sınıfı ile işlem yapar.
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<> (doctorService.getAllResponses (), HttpStatus.OK);
    }
// Burada getAll metodu oluşturuldu ve bu metod, doctorService sınıfının getAllResponses metodunu çağırır. Bu metod, tüm doktorların bilgilerini döndürür.
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<> (doctorService.getResponseById (id), HttpStatus.OK);
    }
// Burada getById metodu oluşturuldu ve bu metod, doctorService sınıfının getResponseById metodunu çağırır. Bu metod, belirli bir doktorun bilgilerini döndürür.
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DoctorRequest doctorRequest) {
        return new ResponseEntity<> (doctorService.create (doctorRequest), HttpStatus.CREATED);
    }
//
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody DoctorRequest doctorRequest
    ) {
        return new ResponseEntity<> (doctorService.update (id, doctorRequest), HttpStatus.ACCEPTED);
    }
//  Burada update metodu oluşturuldu ve bu metod, doctorService sınıfının update metodunu çağırır. Bu metod, belirli bir doktoru günceller.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        doctorService.deleteById (id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }
}
