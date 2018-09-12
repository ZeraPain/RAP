package de.h_da.library.datamanagement.usecase.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.h_da.library.configuration.LibraryTest;
import de.h_da.library.datamanagement.entity.Book;
import de.h_da.library.datamanagement.entity.Customer;
import de.h_da.library.datamanagement.manager.BookManager;
import de.h_da.library.datamanagement.manager.CustomerManager;
import de.h_da.library.datamanagement.usecase.Search;

@RunWith(Arquillian.class)
public class SearchImplTest extends LibraryTest{
	@EJB
    BookManager bookManager;
	@EJB
	CustomerManager customerManager;
	@EJB
    Search search;
	
	@Test
	public void testManagersAndUseCaseNotNull() {
		assertNotNull(bookManager);
		assertNotNull(customerManager);
		assertNotNull(search);
	}
	
	@Test
	public void testFindBooksByAttributes() {
		Book book1, book2, book3;

        // preparation
        book1 = new Book();
        book1.setAuthors("Schmitt");
        book1.setTitle("Der Hof"); 
        
        book2 = new Book();        
        book2.setAuthors("Maier");
        book2.setTitle("Der Hof");
        
        book3 = new Book();
        book3.setAuthors("Schmitt");
        book3.setTitle("Die Wiese");

        // execution
        bookManager.create(book1);
        bookManager.create(book2);
        bookManager.create(book3);
        
        // evaluation
        Book filter1 = new Book();
        filter1.setAuthors("Schmitt");
        filter1.setTitle("Der Hof");
        
        ArrayList<Book> foundBooks = (ArrayList<Book>) search.findBooksByAttributes(filter1);
        
        assertNotNull(foundBooks);
        assertEquals(1,foundBooks.size());
        assertEquals("filter1: book1 not found",foundBooks.get(0).getTitle()+foundBooks.get(0).getAuthors(),book1.getTitle()+book1.getAuthors());
                
        Book filter2 = new Book();
        filter2.setTitle("Der Hof");
        
        ArrayList<Book> foundBooks2 = (ArrayList<Book>) search.findBooksByAttributes(filter2);
        
        assertNotNull(foundBooks2);
        assertEquals(2,foundBooks2.size());
        assertEquals("filter2: book1 not found",foundBooks2.get(0).getTitle()+foundBooks2.get(0).getAuthors(),book1.getTitle()+book1.getAuthors());
        assertEquals("filter2: book2 not found",foundBooks2.get(1).getTitle()+foundBooks2.get(1).getAuthors(),book2.getTitle()+book2.getAuthors());
              
        
        /*
        ArrayList<Book> expactedBooks = (ArrayList<Book>) bookManager.findAll();      
        
        boolean match = false;
        
        for(Book found: foundBooks) {
        	for(Book exp: expactedBooks) {
        		if(found.getTitle().equals(exp.getTitle()) && found.getAuthors().equals(exp.getAuthors())) {
        			match = true;
        		}else {
        			match = false;
        		}
        	}
        }
        
        assertTrue("##### FAILURE: " + foundBooks.size() +" #####", match);
        */
	}
	
	@Test
	public void testFindCustomersByAttributes() {
		Customer customer1, customer2, customer3;

        // preparation
        customer1 = new Customer();
        customer1.setName("Schmitt");
        customer1.setAddress("Hinterhof 1"); 
        
        customer2 = new Customer();        
        customer2.setName("Maier");
        customer2.setAddress("Hinterhof 1");
        
        customer3 = new Customer();
        customer3.setName("Schmitt");
        customer3.setAddress("Wiesenstraße 42");

        // execution
        customerManager.create(customer1);
        customerManager.create(customer2);
        customerManager.create(customer3);
        
        // evaluation
        /*
        Customer filter = new Customer();
        filter.setName("Schmitt");
        filter.setAddress("Hinterhof 1");
        
        ArrayList<Customer> foundCustomers = (ArrayList<Customer>) search.findCustomersByAttributes(filter);
        
        ArrayList<Customer> expactedCustomers = (ArrayList<Customer>) customerManager.findAll();      
        
        boolean match = false;
        
        for(Customer found: foundCustomers) {
        	for(Customer exp: expactedCustomers) {
        		if(found.getName().equals(exp.getName()) && found.getAddress().equals(exp.getAddress())) {
        			match = true;
        		}else {
        			match = false;
        		}
        	}
        }
        
        assertTrue("##### FAILURE: " + foundCustomers.size() +" #####", match);
        */
        Customer filter1 = new Customer();
        filter1.setName("Schmitt");
        filter1.setAddress("Hinterhof 1");
        
        ArrayList<Customer> foundCustomers = (ArrayList<Customer>) search.findCustomersByAttributes(filter1);
        
        assertNotNull(foundCustomers);
        assertEquals(1,foundCustomers.size());
        assertEquals("filter1: customer1 not found",foundCustomers.get(0).getName()+foundCustomers.get(0).getAddress(),customer1.getName()+customer1.getAddress());
        
        
        Customer filter2 = new Customer();
        filter2.setAddress("Hinterhof 1");
        
        ArrayList<Customer> foundCustomers2 = (ArrayList<Customer>) search.findCustomersByAttributes(filter2);
        
        assertNotNull(foundCustomers2);
        assertEquals(2,foundCustomers2.size());
        assertEquals("filter2: customer1 not found",foundCustomers2.get(0).getName()+foundCustomers2.get(0).getAddress(),customer1.getName()+customer1.getAddress());
        assertEquals("filter2: customer2 not found",foundCustomers2.get(1).getName()+foundCustomers2.get(1).getAddress(),customer2.getName()+customer2.getAddress());
        
	}
}
