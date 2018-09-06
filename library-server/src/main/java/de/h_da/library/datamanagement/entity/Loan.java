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

import javax.persistence.Basic;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class Loan
 *
 */
@Entity
public class Loan implements Serializable {
    
	private Date loanDate;
	
	private Date dueDate;
	
	private Customer customer;
	
	private BookOnStock bookOnStock;
	
	private LoanStatus status;
	
	
    /** Creates a new instance of Entity1 */
    public Loan() {
    }

}
