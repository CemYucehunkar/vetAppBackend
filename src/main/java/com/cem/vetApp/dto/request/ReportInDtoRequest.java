package com.cem.vetApp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportInDtoRequest {
    @Positive
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String diagnosis;

    @PositiveOrZero
    private double price;
}
