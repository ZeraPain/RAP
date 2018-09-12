package de.h_da.library.datamanagement.manager.impl;

/*
 * ReminderManager.java
 *
 * Created on 10. September 2007, 14:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */


import de.h_da.library.datamanagement.entity.Reminder;
import de.h_da.library.datamanagement.manager.ReminderManager;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ReminderManagerImpl implements ReminderManager {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of ReminderManagerImpl */
    public ReminderManagerImpl() {
    }
    
    @Override
    public Reminder create(Reminder reminder) {
        em.persist(reminder);
        return reminder;
    }

    @Override
    public void edit(Reminder reminder) {
        em.merge(reminder);
    }

    @Override
    public void destroy(Reminder reminder) {
        em.remove(em.merge(reminder));
    }

    @Override
    public Reminder findById(Long id) {
        return (Reminder) em.find(Reminder.class, id);
    }

    @Override
    public List<Reminder> findAll() {
        return em.createQuery("select object(o) from Reminder as o").getResultList();
    }
    
}

