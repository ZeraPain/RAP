package de.h_da.library.datamanagement.manager;

import java.util.List;

import javax.ejb.Local;

import de.h_da.library.datamanagement.entity.Customer;

@Local
public interface CustomerManager {
    void edit(Customer customer);

    void destroy(Customer customer);

    Customer findById(Long id);

    List<Customer> findAll();

    Customer create(Customer customer);
}
