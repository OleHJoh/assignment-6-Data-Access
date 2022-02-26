package no.noroff.accelerate.assignment6DataAccess.controllers;

import no.noroff.accelerate.assignment6DataAccess.models.Customer;
import no.noroff.accelerate.assignment6DataAccess.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        @GetMapping("search")
        public List<Customer> getCustomerByName(@RequestParam(value = "name", defaultValue = "") String name){
            return customerRepository.getByName(name);
        }

        @GetMapping("limit")
        public List<Customer> getCustomersLimit(@RequestParam(value = "limit", defaultValue = "10") String limit, @RequestParam(value = "offset", defaultValue = "50") String offset){
            return customerRepository.getOffsetLimit(offset,limit);
        }
}
