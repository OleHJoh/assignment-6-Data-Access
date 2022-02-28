package no.noroff.accelerate.assignment6DataAccess.controllers;

import no.noroff.accelerate.assignment6DataAccess.models.Country;
import no.noroff.accelerate.assignment6DataAccess.models.Customer;
import no.noroff.accelerate.assignment6DataAccess.models.CustomerInvoiceJoined;
import no.noroff.accelerate.assignment6DataAccess.repositories.CustomerInvoiceRepository;
import no.noroff.accelerate.assignment6DataAccess.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerApi {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerInvoiceRepository customerInvoiceRepository;

    @GetMapping
    public List<Customer> getAll(){
        return customerRepository.getAll();
    }

    @GetMapping("{id}")
    public Customer getCustomerById(@PathVariable String id){
        return customerRepository.getById(id);
    }

    @GetMapping("search")
    public List<Customer> getCustomerByName(@RequestParam(value = "name", defaultValue = "") String name) {
        return customerRepository.getByName(name);
    }

    @GetMapping("limit")
    public List<Customer> getCustomersLimit(@RequestParam(value = "limit", defaultValue = "10") String limit, @RequestParam(value = "offset", defaultValue = "0") String offset) {
        return customerRepository.getOffsetLimit(offset,limit);
    }

    @PostMapping("add")
    public int postCustomer(@RequestBody Customer customer) {
        return customerRepository.add(customer);
    }

    @PatchMapping("update")
    public int patchCustomer(@RequestBody Customer customer) {
        return customerRepository.update(customer);
    }

    @GetMapping("countries")
    public List<Country> getCustomerNumbersForCountries() {
        return customerRepository.customerListForCountries();
    }

    @GetMapping("spenders")
    public List<CustomerInvoiceJoined> getSpenders(){
        return customerInvoiceRepository.getSpenderList();
    }
}
