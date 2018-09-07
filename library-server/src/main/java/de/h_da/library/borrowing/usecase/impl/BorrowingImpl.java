package de.h_da.library.borrowing.usecase.impl;

import de.h_da.library.borrowing.usecase.Borrowing;

import java.util.Collection;
import java.util.Date;
import java.util.Calendar;

import javax.ejb.EJB;

import de.h_da.library.LibraryException;
import de.h_da.library.datamanagement.entity.BookOnStock;
import de.h_da.library.datamanagement.entity.Loan;
import de.h_da.library.datamanagement.entity.Customer;
import de.h_da.library.datamanagement.manager.BookOnStockManager;
import de.h_da.library.datamanagement.manager.CustomerManager;
import de.h_da.library.datamanagement.manager.LoanManager;

import de.h_da.library.datamanagement.type.LoanStatus;

public class BorrowingImpl implements Borrowing{
	@EJB()
	BookOnStockManager bookOnStockManager;
	@EJB()
	CustomerManager customerManager;
	@EJB()
	LoanManager loanManager;
	
	public BorrowingImpl() {
    }
	
	/**
	 * [command] Generates a new <code>Loan</code> object.
	 * 
	 * @param bookOnStockId
	 *            [in] id of <code>BookOnStock</code> to be borrowed
	 * @param customerId
	 *            [in] id of <code>Customer</code> who borrows the book
	 * @return [out] id of generated <code>Loan</code> object
	 * 
	 * <pre>
	 *    [pre exists Customer c: c.id == customerId
	 *     pre exists BookOnStock boS: boS.id == bookOnStockId
	 *     post exists new Loan l:
	 *       l.id == result
	 *       &amp;&amp; l.loanDate is current date
	 *       &amp;&amp; l.dueDate is current date plus 30 days
	 *       &amp;&amp; l.customer.id == customerId
	 *       &amp;&amp; l.bookOnStock.id == bookOnStockId
	 *       &amp;&amp; l.reminder == null
	 *       &amp;&amp; l.status == LoanStatus.BORROWED
	 *     ]
	 * </pre>
	 * @throws LibraryException 
	 */
	@Override
	public Long borrowBook(Long bookOnStockId, Long customerId) {		
		Customer customer = customerManager.findById(customerId);
		if(customer == null) {
			//throw LibraryException();
		}
		BookOnStock bookOnStock = bookOnStockManager.findById(bookOnStockId);
		if(bookOnStock == null) {
			//throw LibraryException();
		}
		Date loanDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		
		Loan loan = new Loan();
		loan.setLoanDate(loanDate);
		loan.setDueDate(calendar.getTime());
		loan.setCustomer(customer);
		loan.setBookOnStock(bookOnStock);
		loan.setStatus(LoanStatus.BORROWED);
		
		Loan loanCreated = loanManager.create(loan);
		if (loanCreated == null) {
			//throw LibraryException();
		}
		return loanCreated.getLoanId();
	}
	
	/**
	 * [command] Marks a <code>Loan</code> as being returned.
	 * 
	 * @param loanId
	 *            [in] id of <code>Loan</code> object
	 * 
	 * <pre>
	 *   [pre exists Loan l (l.id == loanId)
	 *    pre l.status != LoanStatus.RETURNED
	 *    post l.status == LoanStatus.RETURNED
	 *   ]
	 * </pre>
	 */
	@Override
	public void returnBook(Long loanId) {
		Loan loan = loanManager.findById(loanId);
		if (loan == null) {
			//throw ERROR
		}
		loan.setStatus(LoanStatus.RETURNED);
		
		loanManager.edit(loan);
		
		Loan loanEdited = loanManager.findById(loanId);
		if (loanEdited == null) {
			//throw ERROR
		}
		if (loanEdited.getStatus() != LoanStatus.RETURNED) {
			//throw ERROR
		}
	}

}