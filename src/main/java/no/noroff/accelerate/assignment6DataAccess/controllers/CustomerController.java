package no.noroff.accelerate.assignment6DataAccess.controllers;

import no.noroff.accelerate.assignment6DataAccess.repositories.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("customers")
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("customers", customerRepository.getAll());
        return "view-customers";
    }

    @GetMapping("{id}")
    public String getCustomerById(@PathVariable String id, Model model){
       model.addAttribute("customer", customerRepository.getById(id));
       return "view-customer";
    }

    @GetMapping("search?name={name}")
    public String getCustomerByName(@PathVariable String name, Model model){
        model.addAttribute("customer", customerRepository.getByName(name));
        return "view-customer";
    }



}
