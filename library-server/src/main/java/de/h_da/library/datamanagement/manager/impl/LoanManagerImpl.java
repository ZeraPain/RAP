package de.h_da.library.datamanagement.manager.impl;

import de.h_da.library.datamanagement.entity.Loan;
import de.h_da.library.datamanagement.manager.LoanManager;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class LoanManagerImpl implements LoanManager {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of LoanFacade */
    public LoanManagerImpl() {
    }
    
    @Override
    public Loan create(Loan loan) {
        em.persist(loan);
        return loan;
    }
    
    @Override
    public void edit(Loan loan) {
        em.merge(loan);
    }

    @Override
    public void destroy(Loan loan) {
        em.remove(em.merge(loan));
    }
    
    @Override
    public Loan findById(Long loanId) {
        return (Loan) em.find(Loan.class, loanId);
    }
    
    @Override
    public List<Loan> findAll() {
        return em.createQuery("select object(o) from Loan as o").getResultList();
    }

}
