package no.noroff.accelerate.assignment6DataAccess.repositories;

import no.noroff.accelerate.assignment6DataAccess.models.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> getAll();
    Customer getById(String id);
    List<Customer> getByName(String name);
    List<Customer> getOffsetLimit(String offset, String limit);
    int add(Customer customer);
    int update(Customer customer);
    int delete(String id);
}
