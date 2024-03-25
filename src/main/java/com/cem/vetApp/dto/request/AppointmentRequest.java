package com.cem.vetApp.dto.request;

import com.cem.vetApp.entity.Doctor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentRequest {
    private LocalDateTime appointmentDate;

    private Doctor doctor;

    private AnimalInDtoRequest animal;
}
