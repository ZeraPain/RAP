package de.h_da.library.registration.usecase.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import de.h_da.library.RegistrationException;
import de.h_da.library.accounting.entity.Invoice;
import de.h_da.library.accounting.manager.InvoiceManager;
import de.h_da.library.datamanagement.entity.Customer;
import de.h_da.library.datamanagement.manager.CustomerManager;
import de.h_da.library.registration.usecase.Registration;
import de.h_da.library.registration.usecase.RegistrationRemote;
 
@Stateless
public class RegistrationImpl implements Registration, RegistrationRemote{
	
	@EJB
	CustomerManager customerManager;
	
	@EJB
	InvoiceManager invoiceManager;
	
	
	public RegistrationImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Long register(Customer customer) throws RegistrationException {
		
		List<Customer> customerCollection =  customerManager.findAll();
		Customer foundCustomer = customerCollection.stream().filter(c -> c.getId().equals(customer.getId())).findFirst().orElse(new Customer());
		if(foundCustomer.getId() == null) {
			customerExists(customer, customerCollection);
		}else {
			throw new RegistrationException("Customer ID already exists");
		}
		
		Invoice newInvoice = new Invoice();
		newInvoice.setAddress(customer.getAddress());
		newInvoice.setName(customer.getName());
		newInvoice.setSubject("Registration Fee");
		newInvoice.setAmount(5);
		newInvoice.setDate(new Date());
		
		List<Invoice> invoiceList =  invoiceManager.findAll();
		if(invoiceList.contains(newInvoice)) {
			//throw
		}
		invoiceManager.create(newInvoice);
		
		Customer newCustomer = customerManager.create(customer);
		
		
		return newCustomer.getId();
	}
	
	private void customerExists(Customer customer, List<Customer> customerCollection) throws RegistrationException {
		for (Customer c : customerCollection) {

			if(c.getAddress().equals(customer.getAddress())) {
				throw new RegistrationException("Customer Address already exists");
			}
			if(c.getName().equals(customer.getName())) {
				throw new RegistrationException("Customer Name already exists");
			}
		}
	}

	@Override
	public void modifyRestistration(Customer customer) throws RegistrationException {
		Customer storedCustomer = customerManager.findById(customer.getId());
		if(storedCustomer.getId() != customer.getId()) {
			throw new RegistrationException("Customer ID" + customer.getId().toString() + " does not exist");
		}
		customerManager.edit(storedCustomer);
	}

}
