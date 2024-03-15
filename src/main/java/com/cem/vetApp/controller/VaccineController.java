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

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<> (vaccineService.getAllResponses (), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<> (vaccineService.getResponseById (id), HttpStatus.OK);
    }

    @GetMapping("start-date/{startDate}/finish-date/{finishDate}")
    public ResponseEntity<?> getAllVaccinesBetweenStartAndFinishDate(
            @PathVariable("startDate") LocalDate startDate,
            @PathVariable("finishDate") LocalDate finishDate
    ) {
        return new ResponseEntity<> (vaccineService.getAllVaccinesBetweenStartAndFinishDate (startDate, finishDate), HttpStatus.OK);
    }

    @GetMapping("/filter-by-animal-name/{animalName}")
    public ResponseEntity<?> getByAnimalName(@PathVariable("animalName") String animalName) {
        return new ResponseEntity<> (vaccineService.getByAnimalName (animalName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody VaccineRequest vaccineRequest) {
        return new ResponseEntity<> (vaccineService.create (vaccineRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody VaccineRequest vaccineRequest
    ) {
        return new ResponseEntity<> (vaccineService.update (id, vaccineRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        vaccineService.deleteById (id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }
}
