package no.noroff.accelerate.assignment6DataAccess.repositories;

import no.noroff.accelerate.assignment6DataAccess.models.CustomerInvoiceJoined;

import java.util.List;

//Repository for Customer and Invoice Joined handling
public interface CustomerInvoiceRepository {

    List<CustomerInvoiceJoined> getSpenderList();
}
