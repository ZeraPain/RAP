package de.h_da.library.datamanagement.usecase;

import java.util.List;

import javax.ejb.Remote;

import de.h_da.library.datamanagement.entity.Book;
import de.h_da.library.datamanagement.entity.Customer;

@Remote
public interface SearchRemote {
	List<Book> findBooksByAttributes(Book book);
	List<Customer> findCustomersByAttributes(Customer customer);
}
