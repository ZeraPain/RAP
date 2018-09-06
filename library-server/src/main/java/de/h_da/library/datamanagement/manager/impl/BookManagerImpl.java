package de.h_da.library.datamanagement.manager.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.h_da.library.component1.entity.Entity1;
import de.h_da.library.datamanagement.entity.Book;
import de.h_da.library.datamanagement.manager.BookManager;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BookManagerImpl implements BookManager{

    @PersistenceContext
    private EntityManager em;
	
	public BookManagerImpl() {}
	
	@Override
	public Book create(Book book) {
		em.persist(book);
		return book;
	} 
	
	@Override
	public void edit(Book book) {
		em.merge(book);
	}

	@Override
	public void destroy(Book book) {
		em.remove(em.merge(book));
		
	}

	@Override
	public Book findById(Long id) {
		return (Book) em.find(Book.class, id);
	}

	@Override
	public List<Book> findAll() {
		return em.createQuery("select object(o) from Book as o").getResultList();
	}
}
