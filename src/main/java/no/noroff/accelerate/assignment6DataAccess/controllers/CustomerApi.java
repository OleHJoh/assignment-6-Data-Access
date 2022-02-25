package no.noroff.accelerate.assignment6DataAccess.controllers;

import no.noroff.accelerate.assignment6DataAccess.models.Customer;
import no.noroff.accelerate.assignment6DataAccess.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerApi {

        @Autowired
        private CustomerRepository customerRepository;

        @GetMapping
        public List<Customer> getAll(){
            return customerRepository.getAll();
        }

        @GetMapping("{id}")
        public Customer getCustomerById(@PathVariable String id){
            return customerRepository.getById(id);
        }

        @GetMapping("search?name={name}")
        public Customer getCustomerByName(@PathVariable String name){
            return customerRepository.getByName(name);
        }
}
