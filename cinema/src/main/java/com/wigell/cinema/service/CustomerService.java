package com.wigell.cinema.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.wigell.cinema.entity.Address;
import com.wigell.cinema.entity.Customer;
import com.wigell.cinema.repository.AddressRepository;
import com.wigell.cinema.repository.CustomerRepository;

@Service
public class CustomerService {
    private static final Logger logger = Logger.getLogger(CustomerService.class.getName());

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository){
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer){
        Customer saved = customerRepository.save(customer);
        logger.info("Admin added customer" + saved.getName());
        return saved;
    }

    public void deleteCustomer(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found" + id));
        customerRepository.deleteById(id);
        logger.info("Admin deleted customer" + customer.getName());
    }

    public Customer updaCustomer(Long id, Customer updatedCustomer){
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer was not found" + id));
        existing.setName(updatedCustomer.getName());
        existing.setUsername(updatedCustomer.getUsername());
        Customer saved = customerRepository.save(existing);
        logger.info("Admin updated Customer" + id);
        return saved;
    }

    public Address addAdress(Long customerId, Address address){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found" + customerId));
        address.setCustomer(customer);
        Address saved = addressRepository.save(address);
        logger.info("Admin added address to customer" + customer.getName());
        return saved;
    }

    public void deleteAddress(Long customerId, Long addressId){
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found" + addressId));
        addressRepository.deleteById(addressId);
        logger.info("Admin deleted address from customer id" + customerId);
    }
}
