package de.h_da.library.registration.usecase.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.h_da.library.datamanagement.entity.Customer;
import de.h_da.library.datamanagement.manager.CustomerManager;
import de.h_da.library.registration.usecase.Registration;
import de.h_da.library.registration.usecase.RegistrationRemote;

@Stateless
public class RegistrationImpl implements Registration, RegistrationRemote{
	
	@EJB
	CustomerManager customerManager;
	
	
	public RegistrationImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int register(Customer customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void modifyRestistration(Customer customer) {
		// TODO Auto-generated method stub
		
	}

}
