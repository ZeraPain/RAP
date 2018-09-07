/*
 * UseCase1ImplTest.java
 * JUnit based test
 *
 * Created on 10. September 2007, 18:26
 * Updated on 05. July 2012
 */

package de.h_da.library.datamanagement.usecase.impl;

import de.h_da.library.configuration.LibraryTest;
import de.h_da.library.datamanagement.entity.Book;
import de.h_da.library.datamanagement.entity.BookOnStock;
import de.h_da.library.datamanagement.manager.BookManager;
import de.h_da.library.datamanagement.manager.BookOnStockManager;
import de.h_da.library.datamanagement.usecase.BookManagement;

import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class BookManagementImpTest extends LibraryTest {

    @EJB
    BookOnStockManager bookOnStockManager;
    
    @EJB
    BookManagement bookManagement;

    @EJB
    BookManager bookManager;
    
    /**
     * Test of useCaseMethod1 method, of class de.h_da.library.component1.usecase.impl.UseCase1Impl.
     */
    @Test
    public void testAddBook() {
        Book book = new Book();
        book.setTitle("Hallo");
        book.setAuthors("Welt");
  
        List<BookOnStock> booksOnStockFoundBefore, booksOnStockFoundAfter;
        booksOnStockFoundBefore = bookOnStockManager.findAll();
        
        int bookId = bookManagement.addBook(book, 10);
        
        booksOnStockFoundAfter = bookOnStockManager.findAll();
        
        // evaluation
        assertEquals(10, booksOnStockFoundAfter.size() - booksOnStockFoundBefore.size());
        assertNotNull(bookId);
    }
    
    @Test
    public void testModifyBook() {
    	Book book = new Book();
        book.setTitle("Hallo");
        book.setAuthors("Welt");
        
        bookManagement.addBook(book, 1);
        
        book.setTitle("Adee");
        bookManagement.modifyBook(book);
        
        List<Book> books = bookManager.findAll();
		if(book != null) 
		{
			for (Book b : books)
			{
				if (b.getId() == book.getId())
				{
					assertEquals("Adee", b.getTitle());
				}
			}							
		}
    }


}
