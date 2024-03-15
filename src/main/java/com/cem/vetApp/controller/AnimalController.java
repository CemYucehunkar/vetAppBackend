package com.cem.vetApp.controller;

import com.cem.vetApp.dto.request.AnimalRequest;
import com.cem.vetApp.service.AnimalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cem.vetApp.config.BaseURL.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<> (animalService.getAllResponses (), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<> (animalService.getResponseById (id), HttpStatus.OK);
    }

    @GetMapping("filter-by-name/{name}")
    public ResponseEntity<?> filterByName(@PathVariable("name") String name) {
        return new ResponseEntity<> (animalService.filterByName (name), HttpStatus.OK);
    }

    @GetMapping("filter-by-customer-name/{customerName}")
    public ResponseEntity<?> filterByCustomerName(@PathVariable("customerName") String customerName) {
        return new ResponseEntity<> (animalService.filterByCustomerName (customerName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AnimalRequest animalRequest) {
        return new ResponseEntity<> (animalService.create (animalRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody AnimalRequest animalRequest
    ) {
        return new ResponseEntity<> (animalService.update (id, animalRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        animalService.deleteById (id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }
}
