package de.h_da.library.registration.usecase.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.h_da.library.LibraryException;
import de.h_da.library.RegistrationException;
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
	public void registerCustomer() throws RegistrationException {
		
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
	public void registerCustomerAndInvoice() throws RegistrationException {
		
        Customer customer, customerCreated;

        // preparation
        customer = new Customer();
        customer.setName("InvoiceCustomer");
        customer.setAddress("InvoiceCustomerAddress");
        
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
        assertEquals("InvoiceCustomerAddress", filteredInvoice.getAddress());
        assertEquals(5, filteredInvoice.getAmount());
	}
	
	@Test(expected=RegistrationException.class)
	public void modifyCustomer() throws RegistrationException {
		
        Customer customer, customerCreated;

        // preparation
        customer = new Customer();
        customer.setName("ModCustomer");
        customer.setAddress("ModCustomerAddress");
        
        Long id = registration.register(customer);

        //execution
        customerCreated = customerManager.findById(id);
        customerCreated.setAddress("NewAddress");
        registration.modifyRestistration(customerCreated);
        
        // evaluation
        assertNotNull(customerCreated);
        assertNotNull(customerCreated.getId());
        assertEquals("ModCustomer", customerCreated.getName());
        assertEquals("NewAddress", customerCreated.getAddress());
	}
	
	//@Test(expected=RegistrationException.class)
	public void customerIdExists() throws RegistrationException {
        Customer customer, customerCreated;

        // preparation
        customer = new Customer();
        customer.setName("Mod1Customer");
        customer.setAddress("Mod1CustomerAddress");
        
        Long id = registration.register(customer);

        //execution
        customerCreated = customerManager.findById(id);
        customerCreated.setAddress("NewAddress");
        customerCreated.setName("NewCustomer");
        Long id2 = registration.register(customer);
	}
	
	//@Test(expected=RegistrationException.class)
	public void customerNameExists() throws RegistrationException {
        Customer customer, customerCreated;

        // preparation
        customer = new Customer();
        customer.setName("Mod2Customer");
        customer.setAddress("Mod2CustomerAddress");
        
        Long id = registration.register(customer);

        //execution
        customerCreated = new Customer();
        customerCreated.setName("Mod2Customer");
        id = registration.register(customerCreated);
        
	}
	
	//@Test(expected=RegistrationException.class)
	public void customerAddressExists() throws RegistrationException {
        Customer customer, customerCreated;

        // preparation
        customer = new Customer();
        customer.setName("Mod3Customer");
        customer.setAddress("Mod3CustomerAddress");
        
        Long id = registration.register(customer);

        //execution
        customerCreated = new Customer();
        customerCreated.setAddress("Mod3CustomerAddress");
        id = registration.register(customerCreated);
	}
	
	//@Test(expected=RegistrationException.class)
	public void modifyExeption() throws RegistrationException {
        Customer customer, customerCreated;

        // preparation
        customer = new Customer();
        customer.setName("Mod4Customer");
        customer.setAddress("Mod4CustomerAddress");
        
        Long id = registration.register(customer);

        //execution
        customerCreated = customerManager.findById(id);
        id = registration.register(customerCreated);
	}
	
	@Test
	public void findCustomer() throws LibraryException {
        Customer customer, customerCreated;

        // preparation
        customer = new Customer();
        customer.setName("Mod5Customer");
        customer.setAddress("Mod5CustomerAddress");
        
        Long id = registration.register(customer);

        //execution
        customerCreated = registration.findCustomerById(id);
        assertEquals(customerCreated.getId(), customer.getId());
	}
	//@Test(expected=LibraryException.class)
	public void findCustomerException() throws LibraryException{
        Customer customer, customerCreated;

        // preparation
        customer = new Customer();
        customer.setName("Mod6Customer");
        customer.setAddress("Mod6CustomerAddress");
        
        Long id = registration.register(customer);

        //execution
        customerCreated = registration.findCustomerById(Long.valueOf("4346564").longValue());
	}
	

}
