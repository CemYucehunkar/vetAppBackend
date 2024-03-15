package com.cem.vetApp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportResponse {
    private Long id;

    private String title;

    private String diagnosis;

    private double price;

    private OnlyAppointmentResponse appointment;

    private List<OnlyVaccineResponse> vaccines;
}
