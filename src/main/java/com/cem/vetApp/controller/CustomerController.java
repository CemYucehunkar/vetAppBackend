package com.cem.vetApp.controller;

import com.cem.vetApp.dto.request.CustomerRequest;
import com.cem.vetApp.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cem.vetApp.config.BaseURL.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<> (customerService.getAllResponses (), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<> (customerService.getResponseById (id), HttpStatus.OK);
    }

    @GetMapping("filter-by-name/{name}")
    public ResponseEntity<?> filterByName(@PathVariable("name") String name) {
        return new ResponseEntity<> (customerService.filterByName (name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity<> (customerService.create (customerRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody CustomerRequest customerRequest
    ) {
        return new ResponseEntity<> (customerService.update (id, customerRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        customerService.deleteById (id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }
}
