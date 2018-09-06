package de.h_da.library.datamanagement.manager.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import de.h_da.library.datamanagement.manager.BookManager;
import de.h_da.library.datamanagement.entity.Book;
import de.h_da.library.configuration.LibraryTest;

@RunWith(Arquillian.class)
public class BookManagerImplTest extends LibraryTest{

	@EJB
	private BookManager bookManager;
	
    @Test
    public void testCreate() {
        Book book, bookCreated;

        // preparation
        book = new Book();
        book.setAuthors("att1");
        book.setTitle("Title");

        // execution
        bookCreated = bookManager.create(book);
        
        // evaluation
        assertNotNull(bookCreated);
        assertNotNull(bookCreated.getId());
        assertEquals("att1", bookCreated.getAuthors());
        assertEquals("Title", bookCreated.getTitle());
    }

    /**
     * Test of edit method, of class de.h_da.library.component1.manager.impl.BookManagerImpl.
     */
    @Test
    public void testEdit() {
        Book bookNew, bookCreated, bookFound;

        // preparation
        bookNew = new Book();
        bookNew.setAuthors("attOld");
        bookNew.setTitle("Title");
        bookCreated = bookManager.create(bookNew);
        bookCreated.setAuthors("attNew");
        bookCreated.setTitle("Title");
        
        // execution
        bookManager.edit(bookCreated);
        
        // evaluation
        bookFound = bookManager.findById(bookCreated.getId());
        assertEquals("attNew", bookFound.getAuthors());
        assertEquals("Title", bookFound.getTitle());
    }

    /**
     * Test of destroy method, of class de.h_da.library.component1.manager.impl.BookManagerImpl.
     */
    @Test
    public void testDestroy() {
        Book newBookRecord, bookCreated, bookFound;

        // preparation
        newBookRecord = new Book();
        bookCreated = bookManager.create(newBookRecord);
        
        // execution
        bookManager.destroy(bookCreated);

        // evaluation
        bookFound = bookManager.findById(bookCreated.getId());
        assertNull(bookFound);
    }

    /**
     * Test of findById method, of class de.h_da.library.component1.manager.impl.BookManagerImpl.
     */
    @Test
    public void testFindById() {
        Book bookNew, bookCreated, bookFound;

        // preparation
        bookNew = new Book();
        bookNew.setAuthors("att1");
        bookNew.setTitle("Title");
        bookCreated = bookManager.create(bookNew);
        
        // execution
        bookFound = bookManager.findById(bookCreated.getId());
        
        // evaluation
        assertNotNull(bookFound);
        assertEquals(bookCreated.getId(), bookFound.getId());
        assertEquals("att1", bookFound.getAuthors());
        assertEquals("Title", bookFound.getTitle());
    }

    /**
     * Test of findAll method, of class de.h_da.library.component1.manager.impl.BookManagerImpl.
     */
    @Test
    public void testFindAll() {
        Book bookNewA, bookNewB;
        List<Book> entities1FoundBefore, entities1FoundAfter;

        // preparation
        entities1FoundBefore = bookManager.findAll();
        bookNewA = new Book();
        bookNewB = new Book();
        bookManager.create(bookNewA);
        bookManager.create(bookNewB);
        
        // execution
        entities1FoundAfter = bookManager.findAll();
        
        // evaluation
        assertEquals(2, entities1FoundAfter.size() - entities1FoundBefore.size());
    }
}
