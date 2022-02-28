package no.noroff.accelerate.assignment6DataAccess.controllers;

import no.noroff.accelerate.assignment6DataAccess.models.Customer;
import no.noroff.accelerate.assignment6DataAccess.models.CustomerInvoiceJoined;
import no.noroff.accelerate.assignment6DataAccess.repositories.CustomerInvoiceRepository;
import no.noroff.accelerate.assignment6DataAccess.repositories.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("customers")
public class CustomerController {

    private CustomerRepository customerRepository;
    private CustomerInvoiceRepository customerInvoiceRepository;
    public CustomerController(CustomerRepository customerRepository, CustomerInvoiceRepository customerInvoiceRepository) {
        this.customerRepository = customerRepository;
        this.customerInvoiceRepository = customerInvoiceRepository;
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

    @GetMapping("name/{name}")
    public String getCustomerByName(@PathVariable String name, Model model){
        model.addAttribute("customer", customerRepository.getByName(name));
        return "view-customer";
    }

    @GetMapping("limit")
    public String getCustomersLimit(@RequestParam(value = "limit", defaultValue = "10") String limit, @RequestParam(value = "offset", defaultValue = "0") String offset, Model model){
        model.addAttribute("customers", customerRepository.getOffsetLimit(offset,limit));
        return "view-customers";
    }

    @GetMapping("add")
    public String showAddCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "create-customer";
    }

    @PostMapping("add")
    public String postCustomer(@ModelAttribute Customer customer, BindingResult errors, Model model){
        model.addAttribute("customer", new Customer());
        return "create-customer";
    }

    @GetMapping("update")
    public String showUpdateCustomer(@RequestParam(value = "id", defaultValue = "1") String id, Model model){
        model.addAttribute("customer", customerRepository.getById(id));
        return "update-customer";
    }

    @PostMapping("update")
    public String updateCustomer(@RequestParam(value = "id", defaultValue = "1") String id, @ModelAttribute Customer customer, BindingResult errors, Model model){
        model.addAttribute("customer", customerRepository.getById(id));
        return "update-customer";
    }

    @GetMapping("countries")
    public String getCustomerNumbersForCountries(Model model) {
        model.addAttribute("countries", customerRepository.customerListForCountries());
        return "view-countries";
    }

    @GetMapping("spenders")
    public String getSpenders(Model model){
        model.addAttribute("customers", customerInvoiceRepository.getSpenderList());
        return "view-spenders";
    }

}
