package com.cem.vetApp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VaccineRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String code;

    private LocalDate protectionStartDate;

    private LocalDate protectionFinishDate;

    private AnimalInDtoRequest animal;

    private ReportInDtoRequest report;
}
