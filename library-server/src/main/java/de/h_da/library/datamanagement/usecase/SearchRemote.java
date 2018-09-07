package de.h_da.library.datamanagement.usecase;

import java.util.Collection;

import javax.ejb.Remote;

import de.h_da.library.datamanagement.entity.Book;
import de.h_da.library.datamanagement.entity.Customer;

@Remote
public interface SearchRemote {
	Collection<Book> findBooksByAttributes(Book book);
	Collection<Customer> findCustomersByAttributes(Customer customer);
}
