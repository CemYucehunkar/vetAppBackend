package com.cem.vetApp.controller;

import com.cem.vetApp.dto.request.VaccineRequest;
import com.cem.vetApp.service.VaccineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.cem.vetApp.config.BaseURL.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "vaccines")
@RequiredArgsConstructor
public class VaccineController {
    private final VaccineService vaccineService;
// VaccineController sınıfı oluşturuldu ve bu sınıfın bir örneği oluşturuldu. Bu sınıf, VaccineService sınıfı ile işlem yapar.
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<> (vaccineService.getAllResponses (), HttpStatus.OK);
    }
// getAll metodu oluşturuldu ve bu metod, vaccineService sınıfının getAllResponses metodunu çağırır. Bu metod, tüm aşıların bilgilerini döndürür.
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<> (vaccineService.getResponseById (id), HttpStatus.OK);
    }
 // getById metodu oluşturuldu ve bu metod, vaccineService sınıfının getResponseById metodunu çağırır. Bu metod, belirli bir aşının bilgilerini döndürür.
    @GetMapping("start-date/{startDate}/finish-date/{finishDate}")
    public ResponseEntity<?> getAllVaccinesBetweenStartAndFinishDate(
            @PathVariable("startDate") LocalDate startDate,
            @PathVariable("finishDate") LocalDate finishDate
    ) {
        return new ResponseEntity<> (vaccineService.getAllVaccinesBetweenStartAndFinishDate (startDate, finishDate), HttpStatus.OK);
    }
// getAllVaccinesBetweenStartAndFinishDate metodu oluşturuldu ve bu metod, vaccineService sınıfının getAllVaccinesBetweenStartAndFinishDate metodunu çağırır. Bu metod, belirli bir tarih aralığındaki tüm aşıların bilgilerini döndürür.
    @GetMapping("/filter-by-animal-name/{animalName}")
    public ResponseEntity<?> getByAnimalName(@PathVariable("animalName") String animalName) {
        return new ResponseEntity<> (vaccineService.getByAnimalName (animalName), HttpStatus.OK);
    }
// getByAnimalName metodu oluşturuldu ve bu metod, vaccineService sınıfının getByAnimalName metodunu çağırır. Bu metod, belirli bir hayvana ait olan aşıların bilgilerini döndürür.
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody VaccineRequest vaccineRequest) {
        return new ResponseEntity<> (vaccineService.create (vaccineRequest), HttpStatus.CREATED);
    }
// create metodu oluşturuldu ve bu metod, vaccineService sınıfının create metodunu çağırır. Bu metod, yeni bir aşı oluşturur.
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody VaccineRequest vaccineRequest
    ) {
        return new ResponseEntity<> (vaccineService.update (id, vaccineRequest), HttpStatus.ACCEPTED);
    }
// update metodu oluşturuldu ve bu metod, vaccineService sınıfının update metodunu çağırır. Bu metod, belirli bir aşıyı günceller.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        vaccineService.deleteById (id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }
}
