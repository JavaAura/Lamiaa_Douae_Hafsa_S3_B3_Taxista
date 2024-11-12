package com.taxi.taxista.service;

import com.taxi.taxista.DTO.CustomerDTO;
import com.taxi.taxista.DTO.DriverDTO;
import com.taxi.taxista.entity.Customer;
import com.taxi.taxista.entity.Driver;
import com.taxi.taxista.mapper.CustomerMapper;
import com.taxi.taxista.mapper.DriverMapper;
import com.taxi.taxista.repository.CustomerRepository;
import com.taxi.taxista.repository.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(DriverService.class);
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDTO saveCustomer(@Valid CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(savedCustomer);
    }

    public CustomerDTO updateCustomer(Long id, @Valid CustomerDTO updatedCustomerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found: " + id));
        customerMapper.updateEntityFromDTO(updatedCustomerDTO, existingCustomer);
        Customer savedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toDTO(savedCustomer);
    }

    public List<CustomerDTO> getAllCustomers() {
        try {
            return customerRepository.findAll()
                    .stream()
                    .map(customerMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error retrieving customers: {}", e.getMessage(), e);
            throw e;
        }
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found: " + id));
        return customerMapper.toDTO(customer);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found: " + id);
        }
        customerRepository.deleteById(id);
    }
}
