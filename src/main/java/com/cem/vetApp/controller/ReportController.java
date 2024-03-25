package com.cem.vetApp.controller;

import com.cem.vetApp.dto.request.ReportRequest;
import com.cem.vetApp.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cem.vetApp.config.BaseURL.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
// ReportController sınıfı oluşturuldu ve bu sınıfın bir örneği oluşturuldu. Bu sınıf, ReportService sınıfı ile işlem yapar.
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<> (reportService.getAllResponses (), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<> (reportService.getResponseById (id), HttpStatus.OK);
    }
// getById metodu oluşturuldu ve bu metod, reportService sınıfının getResponseById metodunu çağırır. Bu metod, belirli bir raporun bilgilerini döndürür.
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ReportRequest reportRequest) {
        return new ResponseEntity<> (reportService.create (reportRequest), HttpStatus.CREATED);
    }
// create metodu oluşturuldu ve bu metod, reportService sınıfının create metodunu çağırır. Bu metod, yeni bir rapor oluşturur.
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody ReportRequest reportRequest
    ) {
        return new ResponseEntity<> (reportService.update (id, reportRequest), HttpStatus.ACCEPTED);
    }
// update metodu oluşturuldu ve bu metod, reportService sınıfının update metodunu çağırır. Bu metod, belirli bir raporu günceller.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        reportService.deleteById (id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }
}
