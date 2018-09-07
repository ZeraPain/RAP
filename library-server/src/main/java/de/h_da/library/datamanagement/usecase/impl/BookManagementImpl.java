/*
 * UseCase1Bean.java
 *
 * Created on 10. September 2007, 15:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.h_da.library.datamanagement.usecase.impl;

import de.h_da.library.LibraryRuntimeException;
import de.h_da.library.component1.entity.Entity1;
import de.h_da.library.component1.manager.Entity1Manager;
import de.h_da.library.component1.usecase.UseCase1;
import de.h_da.library.component1.usecase.UseCase1Remote;
import de.h_da.library.datamanagement.entity.Book;
import de.h_da.library.datamanagement.entity.BookOnStock;
import de.h_da.library.datamanagement.manager.BookManager;
import de.h_da.library.datamanagement.manager.BookOnStockManager;
import de.h_da.library.datamanagement.usecase.BookManagement;

import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

//@Stateless
public class BookManagementImpl implements BookManagement {
    
    @EJB()
    BookManager bookManager;
    
    @EJB()
    BookOnStockManager bookOnStockManager;
    
    /** Creates a new instance of UseCase1Bean */
    public BookManagementImpl() {
    }
    
	public int addBook(Book book, int numberOfBooksOnStock)
	{
		Book newBook = bookManager.create(book);
		long bookId = newBook.getId();
		
		addBooksOnStock((int)bookId, numberOfBooksOnStock);
		return (int)bookId;
	}

	public void modifyBook(Book book)
	{
		List<Book> books = bookManager.findAll();
		if(book != null) 
		{
			for (Book b : books)
			{
				if (b.getId() == book.getId())
				{
					b.setTitle(book.getTitle());
					b.setAuthors(book.getAuthors());
					bookManager.edit(b);
				}
			}							
		}
		
	}
	
	public void addBooksOnStock(int bookId, int numberOfBooksOnStock)
	{
		BookOnStock bookOnStock = new BookOnStock();
		Book book = bookManager.findById((long)bookId);
		bookOnStock.setBook(book);
		
		for (int i = 0; i < numberOfBooksOnStock; ++i)
		{
			bookOnStockManager.create(bookOnStock);
		}
	}
    
}
