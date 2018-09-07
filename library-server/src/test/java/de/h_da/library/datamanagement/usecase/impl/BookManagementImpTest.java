/*
 * UseCase1ImplTest.java
 * JUnit based test
 *
 * Created on 10. September 2007, 18:26
 * Updated on 05. July 2012
 */

package de.h_da.library.datamanagement.usecase.impl;

import de.h_da.library.LibraryRuntimeException;
import de.h_da.library.component1.entity.Entity1;
import de.h_da.library.component1.manager.Entity1Manager;
import de.h_da.library.component1.type.DataType1;
import de.h_da.library.component1.usecase.UseCase1Remote;
import de.h_da.library.configuration.LibraryTest;
import de.h_da.library.datamanagement.entity.Book;
import de.h_da.library.datamanagement.entity.BookOnStock;
import de.h_da.library.datamanagement.manager.BookManager;
import de.h_da.library.datamanagement.manager.BookOnStockManager;
import de.h_da.library.datamanagement.usecase.BookManagement;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.EJBs;
import javax.naming.NamingException;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class BookManagementImpTest extends LibraryTest {

    @EJB
    BookOnStockManager bookOnStockManager;
    @EJB
    BookManagement bookManagement;

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
        
        bookManagement.addBook(book, 10);
        
        booksOnStockFoundAfter = bookOnStockManager.findAll();
        
        // evaluation
        assertEquals(2, booksOnStockFoundAfter.size() - booksOnStockFoundBefore.size());
    }

    /**
     * Test of create method, of class de.h_da.library.component1.usecase.impl.UseCase1Impl.
     */
    @Test
    public void testCreateEntity1() {
        
    }

    @Test
    public void testCreateEntityNull() {
        
    }

}
