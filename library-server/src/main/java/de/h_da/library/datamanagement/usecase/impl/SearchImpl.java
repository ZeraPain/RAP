package de.h_da.library.datamanagement.usecase.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<Book> findBooksByAttributes(Book book) {		
		List<Book> books = bookManager.findAll();
		List<Book> foundBooks = books;
		if(book != null) {			
			if(book.getTitle() != null && book.getAuthors() == null)
				foundBooks = books.stream().filter(b->b.getTitle()!=null && !b.getTitle().isEmpty() && b.getTitle().toUpperCase().equals(book.getTitle().toUpperCase())).collect(Collectors.toList());
			else if(book.getTitle() == null && book.getAuthors() != null)
				foundBooks = books.stream().filter(b->b.getAuthors()!=null && !b.getAuthors().isEmpty() && b.getAuthors().toUpperCase().equals(book.getAuthors().toUpperCase())).collect(Collectors.toList());
			else if(book.getTitle() != null && book.getAuthors() != null)
				foundBooks = books.stream().filter(b->b.getTitle() != null && b.getAuthors() != null && !b.getTitle().isEmpty() && b.getTitle().toUpperCase().equals(book.getTitle().toUpperCase()) 
				&& !b.getAuthors().isEmpty() && b.getAuthors().toUpperCase().equals(book.getAuthors().toUpperCase())).collect(Collectors.toList());
			
		}
		return foundBooks;
	}

	@Override
	public List<Customer> findCustomersByAttributes(Customer customer) {		
		List<Customer> customers = customerManager.findAll();
		List<Customer> foundCustomers = customers;
		if(customer != null) {			
			if(customer.getName() != null && customer.getAddress() == null)
				foundCustomers = customers.stream().filter(c->!c.getName().isEmpty() && c.getName().toUpperCase().equals(customer.getName().toUpperCase())).collect(Collectors.toList());
			else if(customer.getName() == null && customer.getAddress() != null)
				foundCustomers = customers.stream().filter(c->!c.getAddress().isEmpty() && c.getAddress().toUpperCase().equals(customer.getAddress().toUpperCase())).collect(Collectors.toList());
			else if(customer.getName() != null && customer.getAddress() != null)
				foundCustomers = customers.stream().filter(c->!c.getName().isEmpty() && c.getName().toUpperCase().equals(customer.getName().toUpperCase()) 
				&& !c.getAddress().isEmpty() && c.getAddress().toUpperCase().equals(customer.getAddress().toUpperCase())).collect(Collectors.toList());
		}
		return foundCustomers;
	}
}
