/*
 * Entity1.java
 *
 * Created on 10. September 2007, 14:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.h_da.library.datamanagement.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.h_da.library.datamanagement.type.LoanStatus;

/**
 * Entity class Loan
 *
 */
@Entity
public class Loan implements Serializable {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loanId;
	
	private Date loanDate;
	
	private Date dueDate;
	
	private Customer customer;
	
	private BookOnStock bookOnStock;
	
	private LoanStatus status;
	
	
    /** Creates a new instance of Entity1 */
    public Loan() {
    }

    public Date getLoanDate() {
		return loanDate;
	}


	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public BookOnStock getBookOnStock() {
		return bookOnStock;
	}


	public void setBookOnStock(BookOnStock bookOnStock) {
		this.bookOnStock = bookOnStock;
	}


	public LoanStatus getStatus() {
		return status;
	}


	public void setStatus(LoanStatus status) {
		this.status = status;
	}
}
