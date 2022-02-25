package no.noroff.accelerate.assignment6DataAccess.repositories;

import no.noroff.accelerate.assignment6DataAccess.models.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> getAll();
    Customer getById(String id);
    Customer getByName(String name);
    int add(Customer customer);
    int update(Customer customer);
    int delete(String id);
}
