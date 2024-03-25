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
// CustomerController sınıfı oluşturuldu ve bu sınıfın bir örneği oluşturuldu. Bu sınıf, CustomerService sınıfı ile işlem yapar.
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<> (customerService.getAllResponses (), HttpStatus.OK);
    }
// getAll metodu oluşturuldu ve bu metod, customerService sınıfının getAllResponses metodunu çağırır. Bu metod, tüm müşterilerin bilgilerini döndürür.
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<> (customerService.getResponseById (id), HttpStatus.OK);
    }
//  getById metodu oluşturuldu ve bu metod, customerService sınıfının getResponseById metodunu çağırır. Bu metod, belirli bir müşterinin bilgilerini döndürür.
    @GetMapping("filter-by-name/{name}")
    public ResponseEntity<?> filterByName(@PathVariable("name") String name) {
        return new ResponseEntity<> (customerService.filterByName (name), HttpStatus.OK);
    }
// filterByName metodu oluşturuldu ve bu metod, customerService sınıfının filterByName metodunu çağırır. Bu metod, belirli bir isme sahip olan müşterilerin bilgilerini döndürür.
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity<> (customerService.create (customerRequest), HttpStatus.CREATED);
    }
// create metodu oluşturuldu ve bu metod, customerService sınıfının create metodunu çağırır. Bu metod, yeni bir müşteri oluşturur.
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody CustomerRequest customerRequest
    ) {
        return new ResponseEntity<> (customerService.update (id, customerRequest), HttpStatus.ACCEPTED);
    }
// update metodu oluşturuldu ve bu metod, customerService sınıfının update metodunu çağırır. Bu metod, belirli bir müşteriyi günceller.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        customerService.deleteById (id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }
}
