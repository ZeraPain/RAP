/*
 * Entity1FacadeLocal.java
 *
 * Created on 10. September 2007, 14:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.h_da.library.datamanagement.manager;

import java.util.List;

import javax.ejb.Local;

import de.h_da.library.datamanagement.entity.Loan;

/**
 *
 * @author humm
 */
@Local
public interface LoanManager {

    void edit(Loan loan);

    void destroy(Loan loan);

    Loan findById(Long loanId);

    List<Loan> findAll();

    Loan create(Loan loan);
    
}
