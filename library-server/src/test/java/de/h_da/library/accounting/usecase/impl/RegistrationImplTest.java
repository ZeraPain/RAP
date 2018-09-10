package de.h_da.library.accounting.usecase.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.h_da.library.accounting.entity.Invoice;
import de.h_da.library.accounting.manager.InvoiceManager;
import de.h_da.library.configuration.LibraryTest;
import de.h_da.library.datamanagement.entity.Customer;
import de.h_da.library.datamanagement.manager.CustomerManager;
import de.h_da.library.registration.usecase.Registration;

@RunWith(Arquillian.class)
public class RegistrationImplTest extends LibraryTest {
	
	@EJB
	Registration registration;
	
	@EJB
	CustomerManager customerManager;
	
	@EJB
	InvoiceManager invoiceManager;
	
	@Test
	public void registerCustomer() {
		
        Customer customer, customerCreated;

        // preparation
        customer = new Customer();
        customer.setName("att1");
        customer.setAddress("Title");
        
        Long id = registration.register(customer);
        // execution
       customerCreated = customerManager.findById(id);
        
        // evaluation
        assertNotNull(customerCreated);
        assertNotNull(customerCreated.getId());
        assertEquals("att1", customerCreated.getName());
        assertEquals("Title", customerCreated.getAddress());
	
	}
	
	@Test
	public void registerCustomerAndInvoice() {
		
        Customer customer, customerCreated;

        // preparation
        customer = new Customer();
        customer.setName("InvoiceCustomer");
        customer.setAddress("Title");
        
        Long id = registration.register(customer);
        // execution
        customerCreated = customerManager.findById(id);
        List<Invoice> invoiceList = invoiceManager.findAll();
        
        Invoice filteredInvoice = invoiceList.stream()
        		.filter(invoice -> invoice.getName()
        		.equals("InvoiceCustomer"))
        		.findFirst().orElse(new Invoice());
        
        // evaluation
        assertNotNull(customerCreated);
        assertNotNull(customerCreated.getId());
        assertEquals("InvoiceCustomer", filteredInvoice.getName());
        assertEquals("Title", filteredInvoice.getAddress());
        assertEquals(5, filteredInvoice.getAmount());
        
        
	
	}
	

}
