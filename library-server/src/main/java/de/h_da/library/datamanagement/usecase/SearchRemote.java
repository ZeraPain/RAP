package de.h_da.library.datamanagement.usecase;

import java.util.List;

import javax.ejb.Remote;

import de.h_da.library.LibraryException;
import de.h_da.library.datamanagement.entity.Book;
import de.h_da.library.datamanagement.entity.Customer;

@Remote
public interface SearchRemote {
	
	List<Customer> findCustomersByAttributes(Customer customer) throws LibraryException;
	List<Book> findBooksByAttributes(Book book) throws LibraryException;
}
