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
import de.h_da.library.datamanagement.manager.BookManager;
import de.h_da.library.datamanagement.manager.BookOnStockManager;
import de.h_da.library.datamanagement.usecase.BookManagement;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
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
		return 0;
	}

	public void modifyBook(Book book)
	{
		
	}
	
	public void addBooksOnStock(int bookId, int numberOfBooksOnStock)
	{
		
	}

    
}
