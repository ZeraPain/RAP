/*
 * Entity1ManagerImplTest.java
 * JUnit based test
 *
 * Created on 10. September 2007, 15:54
 * Updated on 05. July 2012
 */
package de.h_da.library.datamanagement.manager.impl;

import de.h_da.library.component1.type.DataType1;
import de.h_da.library.component1.entity.Entity1;
import de.h_da.library.component1.manager.Entity1Manager;
import de.h_da.library.configuration.LibraryTest;
import de.h_da.library.datamanagement.entity.Loan;
import de.h_da.library.datamanagement.manager.LoanManager;
import de.h_da.library.datamanagement.type.LoanStatus;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.NamingException;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class LoanManagerImplTest extends LibraryTest {

    @EJB
    private LoanManager loanManager;

    /**
     * Test of create method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testCreate() {
        Loan loan, loanCreated;

        // preparation
        loan = new Loan();
        Date now = new Date();
        loan.setLoanDate(now);
        loan.setStatus(LoanStatus.BORROWED);

        // execution
        loanCreated = loanManager.create(loan);
        
        // evaluation
        assertNotNull(loanCreated);
        assertNotNull(loanCreated.getLoanId());
        assertEquals(now, loanCreated.getLoanDate());
        assertEquals(LoanStatus.BORROWED, loanCreated.getStatus());
    }

    /**
     * Test of edit method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testEdit() {
        Loan loanNew, loanCreated, loanFound;

        // preparation
        loanNew = new Loan();
        Date now = new Date();
        loanNew.setLoanDate(now);
        loanNew.setStatus(LoanStatus.BORROWED);
        loanCreated = loanManager.create(loanNew);
        Date now2 = new Date();
        loanNew.setLoanDate(now2);
        loanNew.setStatus(LoanStatus.DELAYED);
        
        // execution
        loanManager.edit(loanCreated);
        
        // evaluation
        loanFound = loanManager.findById(loanCreated.getLoanId());
        assertEquals(now2, loanFound.getDueDate());
        assertEquals(LoanStatus.DELAYED, loanFound.getStatus());
    }

    /**
     * Test of destroy method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testDestroy() {
        Loan newLoanRecord, loanCreated, loanFound;

        // preparation
        newLoanRecord = new Loan();
        loanCreated = loanManager.create(newLoanRecord);
        
        // execution
        loanManager.destroy(loanCreated);

        // evaluation
        loanFound = loanManager.findById(loanCreated.getLoanId());
        assertNull(loanFound);
    }

    /**
     * Test of findById method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testFindById() {
        Loan loanNew, loanCreated, loanFound;

        // preparation
        loanNew = new Loan();
        Date now = new Date();
        loanNew.setLoanDate(now);
        loanNew.setStatus(LoanStatus.BORROWED);
        loanCreated = loanManager.create(loanNew);
        
 
        // execution
        loanFound = loanManager.findById(loanCreated.getLoanId());
        
        // evaluation
        assertNotNull(loanFound);
        assertEquals(loanCreated.getLoanId(), loanFound.getLoanId());
        assertEquals(now, loanFound.getLoanDate());
        assertEquals(LoanStatus.BORROWED, loanFound.getStatus());
    }

    /**
     * Test of findAll method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testFindAll() {
        Loan loanNewA, loanNewB;
        List<Loan> loansFoundBefore, loansFoundAfter;

        // preparation
        loansFoundBefore = loanManager.findAll();
        loanNewA = new Loan();
        loanNewB = new Loan();
        loanManager.create(loanNewA);
        loanManager.create(loanNewB);
        
        // execution
        loansFoundAfter = loanManager.findAll();
        
        // evaluation
        assertEquals(2, loansFoundBefore.size() - loansFoundAfter.size());
    }
}
