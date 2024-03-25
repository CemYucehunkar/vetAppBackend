package com.cem.vetApp.controller;

import com.cem.vetApp.dto.request.AvailableDateRequest;
import com.cem.vetApp.service.AvailableDateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cem.vetApp.config.BaseURL.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "availableDates")
@RequiredArgsConstructor
public class AvailableDateController {
    private final AvailableDateService availableDateService;
// AvailableDateController sınıfı oluşturuldu ve bu sınıfın bir örneği oluşturuldu. Bu sınıf, AvailableDateService sınıfı ile işlem yapar.
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<> (availableDateService.getAllResponses (), HttpStatus.OK);
    }
// getAll metodu oluşturuldu ve bu metod, availableDateService sınıfının getAllResponses metodunu çağırır. Bu metod, tüm uygun tarihlerin bilgilerini döndürür.
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<> (availableDateService.getResponseById (id), HttpStatus.OK);
    }
//  getById metodu oluşturuldu ve bu metod, availableDateService sınıfının getResponseById metodunu çağırır. Bu metod, belirli bir uygun tarihin bilgilerini döndürür.
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AvailableDateRequest availableDateRequest) {
        return new ResponseEntity<> (availableDateService.create (availableDateRequest), HttpStatus.CREATED);
    }
// create metodu oluşturuldu ve bu metod, availableDateService sınıfının create metodunu çağırır. Bu metod, yeni bir uygun tarih oluşturur.
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody AvailableDateRequest availableDateRequest
    ) {
        return new ResponseEntity<> (availableDateService.update (id, availableDateRequest), HttpStatus.ACCEPTED);
    }
// update metodu oluşturuldu ve bu metod, availableDateService sınıfının update metodunu çağırır. Bu metod, belirli bir uygun tarihi günceller.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        availableDateService.deleteById (id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }
}
