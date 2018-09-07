package de.h_da.library.accounting.manager.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.h_da.library.accounting.entity.Invoice;
import de.h_da.library.accounting.manager.InvoiceManager;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class InvoiceManagerImpl implements InvoiceManager{
    @PersistenceContext
    private EntityManager em;
	
	public InvoiceManagerImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Invoice create(Invoice invoice) {
		em.persist(invoice);
		return invoice;
	} 
	
	@Override
	public void edit(Invoice invoice) {
		em.merge(invoice);
	}

	@Override
	public void destroy(Invoice invoice) {
		em.remove(em.merge(invoice));
		
	}

	@Override
	public Invoice findById(Long id) {
		return (Invoice) em.find(Invoice.class, id);
	}

	@Override
	public List<Invoice> findAll() {
		return em.createQuery("select object(o) from Invoice as o").getResultList();
	}
}
