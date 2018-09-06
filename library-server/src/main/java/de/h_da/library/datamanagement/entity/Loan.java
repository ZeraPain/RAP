
package de.h_da.library.datamanagement.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

	@ManyToOne(cascade={CascadeType.MERGE}, fetch=FetchType.EAGER)
	private Customer customer;
	
	private BookOnStock bookOnStock;
	
	private LoanStatus status;
	
	
    /** Creates a new instance of Entity1 */
    public Loan() {
    }

    public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
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
