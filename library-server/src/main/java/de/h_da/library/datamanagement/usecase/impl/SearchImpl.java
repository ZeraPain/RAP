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
		System.out.println("### DEBUG " + this.getClass().toString()+ " books.size: " + books.size());
		//ArrayList<Book> foundBooks = new ArrayList<Book>();
		List<Book> foundBooks = books;
		if(book != null) {
			//remove all books that are not matching the title or the author
			/*
			if(!book.getTitle().isEmpty())
			{
				books.removeIf(b->(b.getTitle().isEmpty() || !b.getTitle().toLowerCase().equals(book.getTitle().toLowerCase())));
			}
			
			if(!book.getAuthors().isEmpty())
				books.removeIf(b->!b.getAuthors().isEmpty() && b.getAuthors().toUpperCase() != book.getAuthors().toUpperCase());
			*/
			
			if(book.getTitle() != null && book.getAuthors() == null)
				foundBooks = books.stream().filter(b->b.getTitle()!=null && !b.getTitle().isEmpty() && b.getTitle().toUpperCase().equals(book.getTitle().toUpperCase())).collect(Collectors.toList());
			else if(book.getTitle() == null && book.getAuthors() != null)
				foundBooks = books.stream().filter(b->b.getAuthors()!=null && !b.getAuthors().isEmpty() && b.getAuthors().toUpperCase().equals(book.getAuthors().toUpperCase())).collect(Collectors.toList());
			else if(book.getTitle() != null && book.getAuthors() != null)
				foundBooks = books.stream().filter(b->b.getTitle() != null && b.getAuthors() != null && !b.getTitle().isEmpty() && b.getTitle().toUpperCase().equals(book.getTitle().toUpperCase()) 
				|| !b.getAuthors().isEmpty() && b.getAuthors().toUpperCase().equals(book.getAuthors().toUpperCase())).collect(Collectors.toList());
				
			/*
			for(Book b: books) {
				if(book.getTitle() != null && book.getAuthors() == null) {
					if(b.getTitle() == book.getTitle())
						foundBooks.add(b);
				}else if(book.getTitle() == null && book.getAuthors() != null) {
					if(b.getAuthors() == book.getAuthors())
						foundBooks.add(b);
				}else if(book.getTitle() != null && book.getAuthors() != null) {
					if(b.getTitle() == book.getTitle()||b.getAuthors() == book.getAuthors())
						foundBooks.add(b);
				}	
			}*/
		}
		return foundBooks;
	}

	@Override
	public List<Customer> findCustomersByAttributes(Customer customer) {
		/*List<Customer> customers = customerManager.findAll();
		if(customer != null) {
			//remove all customers that are not matching the name or the address
			if(!customer.getName().isEmpty())
				customers.removeIf(c->!c.getName().isEmpty() && c.getName().toUpperCase() != customer.getName().toUpperCase());
			if(!customer.getAddress().isEmpty())
				customers.removeIf(c->!c.getAddress().isEmpty() && c.getAddress().toUpperCase() != customer.getAddress().toUpperCase());							
		}
		return customers;
		*/
		List<Customer> customers = customerManager.findAll();
		List<Customer> foundCustomer = customers;
		if(customer != null) {
			//remove all books that are not matching the title or the author
			/*
			if(!book.getTitle().isEmpty())
				books.removeIf(b->!b.getTitle().isEmpty() && b.getTitle().toUpperCase() != book.getTitle().toUpperCase());
			if(!book.getAuthors().isEmpty())
				books.removeIf(b->!b.getAuthors().isEmpty() && b.getAuthors().toUpperCase() != book.getAuthors().toUpperCase());
			*/
			if(customer.getName() != null && customer.getAddress() == null)
				foundCustomer = customers.stream().filter(c->!c.getName().isEmpty() && c.getName().toUpperCase() == customer.getName().toUpperCase()).collect(Collectors.toList());
			else if(customer.getName() == null && customer.getAddress() != null)
				foundCustomer = customers.stream().filter(c->!c.getAddress().isEmpty() && c.getAddress().toUpperCase() == customer.getAddress().toUpperCase()).collect(Collectors.toList());
			else if(customer.getName() != null && customer.getAddress() != null)
				foundCustomer = customers.stream().filter(c->!c.getName().isEmpty() && c.getName().toUpperCase() == customer.getName().toUpperCase() 
				&& !c.getAddress().isEmpty() && c.getAddress().toUpperCase() == customer.getAddress().toUpperCase()).collect(Collectors.toList());
		}
		return foundCustomer;
	}
}
