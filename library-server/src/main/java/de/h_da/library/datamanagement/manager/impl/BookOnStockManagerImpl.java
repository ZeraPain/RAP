package de.h_da.library.datamanagement.manager.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import de.h_da.library.datamanagement.entity.BookOnStock;
import de.h_da.library.datamanagement.manager.BookOnStockManager;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BookOnStockManagerImpl implements BookOnStockManager {
	
    @PersistenceContext
    private EntityManager em;
    
    public BookOnStockManagerImpl() {}

	@Override
	public void edit(BookOnStock bookOnStock) {
		em.merge(bookOnStock);
		
	}

	@Override
	public void destroy(BookOnStock bookOnStock) {
		em.remove(em.merge(bookOnStock));
		
	}

	@Override
	public BookOnStock findById(Long id) {
		return (BookOnStock) em.find(BookOnStock.class, id);
	}

	@Override
	public List<BookOnStock> findAll() {
		return em.createQuery("select object(o) from BookOnStock as o").getResultList();
	}

	@Override
	public BookOnStock create(BookOnStock bookOnStock) {
		em.persist(bookOnStock);
		return bookOnStock;
	}

}
