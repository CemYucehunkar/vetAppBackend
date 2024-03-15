package com.cem.vetApp.service;

import com.cem.vetApp.config.BaseService;
import com.cem.vetApp.config.ModelMapperService;
import com.cem.vetApp.dto.request.CustomerRequest;
import com.cem.vetApp.dto.response.CustomerResponse;
import com.cem.vetApp.entity.Customer;
import com.cem.vetApp.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements BaseService<Customer, CustomerRequest, CustomerResponse> {
    private final CustomerRepository customerRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public Customer getById (Long id)
        {
            return customerRepository.findById(id)
                    .orElseThrow (() -> new EntityNotFoundException ("Customer with ID" + id + " not found"));
        }

    @Override
    public CustomerResponse getResponseById (Long id)
        {
            return modelMapperService
                    .forResponse ()
                    .map (getById (id), CustomerResponse.class);
        }

    @Override
    public List<Customer> getAll ()
        {
            return customerRepository.findAll ();
        }

    @Override
    public List<CustomerResponse> getAllResponses ()
        {
            return getAll ()
                    .stream ().map (customer -> modelMapperService
                            .forResponse ()
                            .map (customer, CustomerResponse.class))
                    .toList ();
        }

    @Override
    public CustomerResponse create (CustomerRequest customerRequest)
        {
            Customer customer = modelMapperService
                    .forRequest ()
                    .map (customerRequest, Customer.class);

            return modelMapperService
                    .forResponse ()
                    .map (customerRepository.save (customer), CustomerResponse.class);
        }

    @Override
    public CustomerResponse update (Long id , CustomerRequest customerRequest)
        {
            Customer doesCustomerExist = getById (id);

            modelMapperService.forRequest ().map (customerRequest, doesCustomerExist);

            return modelMapperService
                    .forResponse ()
                    .map (customerRepository.save (doesCustomerExist), CustomerResponse.class);
        }

    @Override
    public void deleteById (Long id)
        {
            customerRepository.delete (getById (id));
        }

    public List<CustomerResponse> filterByName(String name) {
        return customerRepository.findByName (name)
                .stream ().map (customer -> modelMapperService
                        .forResponse ()
                        .map (customer, CustomerResponse.class))
                .toList ();
    }
}
