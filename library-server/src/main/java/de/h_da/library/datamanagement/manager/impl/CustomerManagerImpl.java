package de.h_da.library.datamanagement.manager.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import de.h_da.library.datamanagement.entity.Customer;
import de.h_da.library.datamanagement.manager.CustomerManager;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CustomerManagerImpl implements CustomerManager{
	
    @PersistenceContext
    private EntityManager em;
    
    public CustomerManagerImpl() {}

	@Override
	public void edit(Customer customer) {
		em.merge(customer);
	}

	@Override
	public void destroy(Customer customer) {
		em.remove(em.merge(customer));
		
	}

	@Override
	public Customer findById(Long id) {
		return (Customer) em.find(Customer.class, id);
		
	}

	@Override
	public List<Customer> findAll() {
		return em.createQuery("select object(o) from Customer as o").getResultList();
	}

	@Override
	public Customer create(Customer customer) {
		em.persist(customer);
		return customer;
	}

}
