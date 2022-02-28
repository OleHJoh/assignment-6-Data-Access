package no.noroff.accelerate.assignment6DataAccess.repositories;

import no.noroff.accelerate.assignment6DataAccess.models.CustomerInvoiceJoined;

import java.util.List;

public interface CustomerInvoiceRepository {

    List<CustomerInvoiceJoined> getSpenderList();
}
