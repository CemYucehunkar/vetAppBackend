package com.cem.vetApp.service;

import com.cem.vetApp.config.BaseService;
import com.cem.vetApp.config.ModelMapperService;
import com.cem.vetApp.dto.request.AvailableDateRequest;
import com.cem.vetApp.dto.response.AvailableDateResponse;
import com.cem.vetApp.entity.AvailableDate;
import com.cem.vetApp.repository.AvailableDateRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateService implements BaseService<AvailableDate, AvailableDateRequest, AvailableDateResponse> {
    private final AvailableDateRepository availableDateRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public AvailableDate getById (Long id)
        {
            return availableDateRepository.findById(id)
                    .orElseThrow (() -> new EntityNotFoundException ("AvailableDate with ID" + id + " not found"));
        }

    @Override
    public AvailableDateResponse getResponseById (Long id)
        {
            return modelMapperService
                    .forResponse ()
                    .map (getById (id), AvailableDateResponse.class);
        }

    @Override
    public List<AvailableDate> getAll ()
        {
            return availableDateRepository.findAll ();
        }

    @Override
    public List<AvailableDateResponse> getAllResponses ()
        {
            return getAll ()
                    .stream ().map (availableDate -> modelMapperService
                            .forResponse ()
                            .map (availableDate, AvailableDateResponse.class))
                    .toList ();
        }

    @Override
    public AvailableDateResponse create (AvailableDateRequest availableDateRequest)
        {
            AvailableDate availableDate = modelMapperService
                    .forRequest ()
                    .map (availableDateRequest, AvailableDate.class);

            return modelMapperService
                    .forResponse ()
                    .map (availableDateRepository.save (availableDate), AvailableDateResponse.class);
        }

    @Override
    public AvailableDateResponse update (Long id , AvailableDateRequest availableDateRequest)
    {
        Optional<AvailableDate> availableDateIdDB=availableDateRepository.findById(id);
        Optional<AvailableDate> isAvailableDateExist=availableDateRepository.findByDoctorIdAndAvailableDate(availableDateRequest.getDoctor().getId(),availableDateRequest.getAvailableDate());
        if (availableDateIdDB.isEmpty()){
            throw new EntityNotFoundException("AvailableDate with ID" + id + " not found");
        }
        if(isAvailableDateExist.isPresent()){
            throw new  EntityNotFoundException("Doctor is not available on this date");
        }

        AvailableDate updatedAvailableDate=availableDateIdDB.get();
        updatedAvailableDate.setAvailableDate(availableDateRequest.getAvailableDate());
        updatedAvailableDate.setDoctor(availableDateRequest.getDoctor());// bu hatayı nasıl düzeltebilirim
        // updatedAvailableDate.setDoctor(availableDateRequest.getDoctor().getId());
        return modelMapperService.forResponse().map(availableDateRepository.save(updatedAvailableDate),AvailableDateResponse.class);





      /*  AvailableDate doesAvailableDateExist = getById (id);
        AvailableDate availableDate = modelMapperService
                .forRequest ()
                .map (availableDateRequest, AvailableDate.class);

        modelMapperService
                .forRequest ()
                .map (availableDate, doesAvailableDateExist);
        doesAvailableDateExist.setId (id);

        return modelMapperService
                .forResponse ()
                .map (availableDateRepository.save (doesAvailableDateExist), AvailableDateResponse.class);*/
    }


    @Override
    public void deleteById (Long id)
        {
            availableDateRepository.delete (getById (id));
        }

    public Optional<AvailableDate> checkIfDoctorIsAvailable(Long doctorId, LocalDate availableDate) {
        return availableDateRepository.findByDoctorIdAndAvailableDate (doctorId, availableDate);
    }
}
