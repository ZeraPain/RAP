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

import de.h_da.library.datamanagement.type.ReminderStatus;

/*
 * Reminder.java
 *
 * Created on 18. September 2007, 11:17
 *
 */

@Entity
public class Reminder  implements Serializable {
	
	private static final long serialVersionUID = -1241882368403321019L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	
	private Date dueDate, issueDate ; 
	private Long fee; 
	private String invoiceId ;
	
	@ManyToOne(cascade={CascadeType.MERGE}, fetch=FetchType.EAGER)
	private Loan loan; 
	private ReminderStatus status ;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public ReminderStatus getStatus() {
		return status;
	}

	public void setStatus(ReminderStatus status) {
		this.status = status;
	}
	
	public void close() {
		System.out.println("TO DO");
	}
}
