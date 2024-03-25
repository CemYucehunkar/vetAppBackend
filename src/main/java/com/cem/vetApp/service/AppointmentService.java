package com.cem.vetApp.service;

import com.cem.vetApp.config.BaseService;
import com.cem.vetApp.config.ModelMapperService;
import com.cem.vetApp.dto.request.AppointmentRequest;
import com.cem.vetApp.dto.response.AppointmentResponse;
import com.cem.vetApp.dto.response.DoctorResponse;
import com.cem.vetApp.entity.Appointment;
import com.cem.vetApp.entity.AvailableDate;
import com.cem.vetApp.repository.AppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService implements BaseService<Appointment, AppointmentRequest, AppointmentResponse> {
    private final AppointmentRepository appointmentRepository;
    private final ModelMapperService modelMapperService;
    private final AvailableDateService availableDateService;

    @Override
    public Appointment getById (Long id)
        {
            return appointmentRepository.findById(id)
                    .orElseThrow (() -> new EntityNotFoundException ("Appointment with ID" + id + " not found"));
        }

    @Override
    public AppointmentResponse getResponseById (Long id)
        {
            return modelMapperService
                    .forResponse ()
                    .map (getById (id), AppointmentResponse.class);
        }

    @Override
    public List<Appointment> getAll ()
        {
            return appointmentRepository.findAll ();
        }

    @Override
    public List<AppointmentResponse> getAllResponses ()
        {
            return getAll ()
                    .stream ().map (appointment -> modelMapperService
                            .forResponse ()
                            .map (appointment, AppointmentResponse.class))
                    .toList ();
        }

    @Override
    public AppointmentResponse create (AppointmentRequest appointmentRequest)
        {
            Appointment appointment = modelMapperService
                    .forRequest ()
                    .map (appointmentRequest, Appointment.class);

            validateAppointment (appointment);

            return modelMapperService
                    .forResponse ()
                    .map (appointmentRepository.save (appointment), AppointmentResponse.class);
        }

    @Override
    public AppointmentResponse update (Long id , AppointmentRequest appointmentRequest)
        {
            Appointment doesAppointmentExist = getById (id); // id 1 appointment

            Appointment appointment = modelMapperService
                    .forRequest ()
                    .map (appointmentRequest, Appointment.class);
            // id null appointment


            modelMapperService
                    .forRequest ()
                    .map (appointment, doesAppointmentExist);
            doesAppointmentExist.setId (id); // database kayıttan önce id setlememiz gerekti
            validateAppointment (doesAppointmentExist);

            return modelMapperService
                    .forResponse ()
                    .map (appointmentRepository.save (doesAppointmentExist), AppointmentResponse.class);
        }

    @Override
    public void deleteById (Long id)
        {
            appointmentRepository.delete (getById (id));
        }

    private void validateAppointment(Appointment appointment) {
        checkIfDoctorIsAvailable(appointment);
        checkIfHourConflictIsPresent(appointment);
    }

    private void checkIfDoctorIsAvailable (Appointment appointment)
        {
            Optional<AvailableDate> optionalAvailableDate = availableDateService.checkIfDoctorIsAvailable (
                    appointment.getDoctor ().getId (),
                    appointment.getAppointmentDate ().toLocalDate ()
            );

            if ( optionalAvailableDate.isEmpty () ) {
                throw new RuntimeException ("Doctor is not available on this day");
            }
        }

    private void checkIfHourConflictIsPresent (Appointment appointment)
        {
            Optional<Appointment> optionalAppointment = appointmentRepository.findByDoctorIdAndAppointmentDate (
                    appointment.getDoctor ().getId (),
                    appointment.getAppointmentDate ()
            );

            if ( optionalAppointment.isPresent () ) {
                throw new RuntimeException ("Hour conflict is present!");
            }
        }

    public List<AppointmentResponse> getByDoctorNameStartAndFinishDate(
            String doctorName,
            LocalDate startDate,
            LocalDate finishDate
    ) {
        return appointmentRepository.findByDoctorNameStartAndFinishDate (doctorName, startDate, finishDate)
                .stream ().map (appointment -> modelMapperService.forResponse ().map (appointment, AppointmentResponse.class))
                .toList ();
    }

    public List<AppointmentResponse> getByAnimalNameStartAndFinishDate(
            String animalName,
            LocalDate startDate,
            LocalDate finishDate
    ) {
        return appointmentRepository.findByAnimalNameStartAndFinishDate (animalName, startDate, finishDate)
                .stream ().map (appointment -> modelMapperService.forResponse ().map (appointment, AppointmentResponse.class))
                .toList ();
    }
}
