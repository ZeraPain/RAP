package de.h_da.library.datamanagement.usecase.impl;

import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.h_da.library.datamanagement.entity.Book;
import de.h_da.library.datamanagement.entity.Customer;
import de.h_da.library.datamanagement.manager.BookManager;
import de.h_da.library.datamanagement.manager.CustomerManager;
import de.h_da.library.datamanagement.usecase.Search;
import de.h_da.library.datamanagement.usecase.SearchRemote;

@Stateless
public class SearchImpl implements Search, SearchRemote {
	@EJB
    BookManager bookManager;
	@EJB
	CustomerManager customerManager;
    
    /** Creates a new instance of UseCase1Bean */
    public SearchImpl() {
    }

	@Override
	public Collection<Book> findBooksByAttributes(Book book) {		
		Collection<Book> books = bookManager.findAll();
		if(book != null) {
			//remove all books that are not matching the title or the author
			if(!book.getTitle().isEmpty())
				books.removeIf(b->!b.getTitle().isEmpty() && b.getTitle().toUpperCase() != book.getTitle().toUpperCase());
			if(!book.getAuthors().isEmpty())
				books.removeIf(b->!b.getAuthors().isEmpty() && b.getAuthors().toUpperCase() != book.getAuthors().toUpperCase());							
		}
		return books;
	}

	@Override
	public Collection<Customer> findCustomersByAttributes(Customer customer) {
		Collection<Customer> customers = customerManager.findAll();
		if(customer != null) {
			//remove all customers that are not matching the name or the address
			if(!customer.getName().isEmpty())
				customers.removeIf(c->!c.getName().isEmpty() && c.getName().toUpperCase() != customer.getName().toUpperCase());
			if(!customer.getAddress().isEmpty())
				customers.removeIf(c->!c.getAddress().isEmpty() && c.getAddress().toUpperCase() != customer.getAddress().toUpperCase());							
		}
		return customers;
	}
}
