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
		Book book1, book2, book3, book4, bookCreated1, bookCreated2, bookCreated3, bookCreated4;

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
        
        book4 = new Book();
        book4.setAuthors("Hallo");
        book4.setTitle("Welt");

        // execution
        bookCreated1 = bookManager.create(book1);
        bookCreated2 = bookManager.create(book2);
        bookCreated3 = bookManager.create(book3);
        bookCreated4 = bookManager.create(book4);
        
        // evaluation
        Book filter = new Book();
        filter.setAuthors("Schmitt");
        filter.setTitle("Der Hof");
        Collection<Book> foundBooks = search.findBooksByAttributes(filter);
        
        assertEquals(1, foundBooks.size());
        
        Book foundBook = (Book)foundBooks.toArray()[0];
        assertEquals(foundBook.getAuthors(), "Schmitt");
        assertEquals(foundBook.getTitle(), "Der Hof");

	}
}
