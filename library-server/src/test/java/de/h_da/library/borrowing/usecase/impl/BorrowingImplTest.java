package de.h_da.library.borrowing.usecase.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import org.junit.Test;

import de.h_da.library.LibraryException;
import de.h_da.library.borrowing.usecase.Borrowing;
import de.h_da.library.configuration.LibraryTest;
import de.h_da.library.datamanagement.entity.Book;
import de.h_da.library.datamanagement.entity.BookOnStock;
import de.h_da.library.datamanagement.entity.Customer;
import de.h_da.library.datamanagement.entity.Loan;
import de.h_da.library.datamanagement.manager.BookManager;
import de.h_da.library.datamanagement.manager.BookOnStockManager;
import de.h_da.library.datamanagement.manager.CustomerManager;
import de.h_da.library.datamanagement.manager.LoanManager;
import de.h_da.library.datamanagement.type.LoanStatus;
import de.h_da.library.datamanagement.usecase.BookManagement;

@RunWith(Arquillian.class)
public class BorrowingImplTest extends LibraryTest {

	@EJB
	BookOnStockManager bookOnStockManager;
	
	@EJB
	BookManagement bookManagement;
	
	@EJB
	CustomerManager customerManager;
	
	@EJB
	LoanManager loanManager;
	
	@EJB
    Borrowing borrowing;
	
	/** Creates a new instance of UseCase1Bean */
    public BorrowingImplTest() {
    }
    
    private void setup() {
    	Book book = new Book();
        book.setTitle("Hallo");
        book.setAuthors("Welt");
        
        try {
			bookManagement.addBook(book, 10);
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Customer testCustomer = new Customer();
        testCustomer.setName("Tester");
        customerManager.create(testCustomer);
    }
    
	@Test
	public void testBorrowBook() {	
		setup();
        
		List<BookOnStock> booksOnStock = bookOnStockManager.findAll();
		BookOnStock bookOnStockToBorrow = booksOnStock.get(0);
		
		List<Customer> customers = customerManager.findAll();
		Customer customer = customers.get(0);
		Long bookOnStockToBorrowId = bookOnStockToBorrow.getId();
		Long customerBorrowingId = customer.getId();
		if (bookOnStockToBorrowId != null && customerBorrowingId != null) {
			try {
				Long loanCreatedId = borrowing.borrowBook(bookOnStockToBorrowId, customerBorrowingId);
				Loan loanCreated = loanManager.findById(loanCreatedId);
	            assertNotNull(loanCreated);
	            assertEquals(loanCreated.getLoanId(), loanCreatedId);
				
			} catch (LibraryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@Test
	public void testReturnBook() {	
		setup();
        
		List<BookOnStock> booksOnStock = bookOnStockManager.findAll();
		BookOnStock bookOnStockToBorrow = booksOnStock.get(0);
		
		List<Customer> customers = customerManager.findAll();
		Customer customer = customers.get(0);
		Long bookOnStockToBorrowId = bookOnStockToBorrow.getId();
		Long customerBorrowingId = customer.getId();
		
		try {
			Long loanCreatedId = borrowing.borrowBook(bookOnStockToBorrowId, customerBorrowingId);
			Loan loanCreated = loanManager.findById(loanCreatedId);
            
            assertNotNull(loanCreated);
            assertEquals(loanCreated.getLoanId(), loanCreatedId);
            
            borrowing.returnBook(loanCreated.getLoanId());
            
            Loan loanReturned = loanManager.findById(loanCreatedId);
            
            assertEquals(loanReturned.getStatus(), LoanStatus.RETURNED);
			
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
