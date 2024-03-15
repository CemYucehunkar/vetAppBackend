package com.cem.vetApp.service;

import com.cem.vetApp.config.BaseService;
import com.cem.vetApp.config.ModelMapperService;
import com.cem.vetApp.dto.request.AnimalRequest;
import com.cem.vetApp.dto.response.AnimalResponse;
import com.cem.vetApp.entity.Animal;
import com.cem.vetApp.repository.AnimalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService implements BaseService<Animal, AnimalRequest, AnimalResponse> {
    private final AnimalRepository animalRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public Animal getById (Long id)
        {
            return animalRepository.findById(id)
                    .orElseThrow (() -> new EntityNotFoundException ("Animal with ID" + id + " not found"));
        }

    @Override
    public AnimalResponse getResponseById (Long id)
        {
            return modelMapperService
                    .forResponse ()
                    .map (getById (id), AnimalResponse.class);
        }

    @Override
    public List<Animal> getAll ()
        {
            return animalRepository.findAll ();
        }

    @Override
    public List<AnimalResponse> getAllResponses ()
        {
            return getAll ()
                    .stream ().map (animal -> modelMapperService
                            .forResponse ()
                            .map (animal, AnimalResponse.class))
                    .toList ();
        }

    @Override
    public AnimalResponse create (AnimalRequest animalRequest)
        {
            Animal animal = modelMapperService
                    .forRequest ()
                    .map (animalRequest, Animal.class);

            return modelMapperService
                    .forResponse ()
                    .map (animalRepository.save (animal), AnimalResponse.class);
        }

    @Override
    public AnimalResponse update (Long id , AnimalRequest animalRequest)
        {
            Animal doesAnimalExist = getById (id);

            modelMapperService.forRequest ().map (animalRequest, doesAnimalExist);

            return modelMapperService
                    .forResponse ()
                    .map (animalRepository.save (doesAnimalExist), AnimalResponse.class);
        }

    @Override
    public void deleteById (Long id)
        {
            animalRepository.delete (getById (id));
        }

    public List<AnimalResponse> filterByName(String name) {
        return animalRepository.findByName (name)
                .stream ().map (animal -> modelMapperService
                        .forResponse ()
                        .map (animal, AnimalResponse.class))
                .toList ();
    }

    public List<AnimalResponse> filterByCustomerName(String customerName) {
        return animalRepository.findByCustomerName (customerName)
                .stream ().map (animal -> modelMapperService
                        .forResponse ()
                        .map (animal, AnimalResponse.class))
                .toList ();
    }
}
