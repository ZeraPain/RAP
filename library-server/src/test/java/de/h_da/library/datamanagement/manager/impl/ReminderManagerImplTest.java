package de.h_da.library.datamanagement.manager.impl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.h_da.library.configuration.LibraryTest;
import de.h_da.library.datamanagement.entity.Reminder;
import de.h_da.library.datamanagement.manager.ReminderManager;
import de.h_da.library.datamanagement.type.ReminderStatus;

@RunWith(Arquillian.class)
public class ReminderManagerImplTest extends LibraryTest{

	@EJB
	private ReminderManager reminderManager;
	
	/**
     * Test of create method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testCreate() {
        Reminder reminder, reminderCreated;

        // preparation
        reminder = new Reminder();
        
        reminder.setInvoiceId("1");;
        

        // execution
        reminderCreated = reminderManager.create(reminder);
        
        // evaluation
        assertNotNull(reminderCreated);
        assertNotNull(reminderCreated.getId());
        assertEquals("1", reminderCreated.getInvoiceId());
        
    }

    /**
     * Test of edit method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testEdit() {
        Reminder entity1New, entity1Created, entity1Found;

        // preparation
        entity1New = new Reminder();
        entity1New.setInvoiceId("500");
        entity1New.setStatus(ReminderStatus.CLOSED);
        entity1Created = reminderManager.create(entity1New);
        entity1Created.setStatus(ReminderStatus.CLOSED);

        
        // execution
        reminderManager.edit(entity1Created);
        
        // evaluation
        entity1Found = reminderManager.findById(entity1Created.getId());
   
        assertEquals(ReminderStatus.CLOSED, entity1Found.getStatus());
    }

    /**
     * Test of destroy method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testDestroy() {
    	Reminder newEntity1Record, entity1Created, entity1Found;

        // preparation
        newEntity1Record = new Reminder();
        entity1Created = reminderManager.create(newEntity1Record);
        
        // execution
        reminderManager.destroy(entity1Created);

        // evaluation
        entity1Found = reminderManager.findById(entity1Created.getId());
        assertNull(entity1Found);
    }

    /**
     * Test of findById method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testFindById() {
        Reminder entity1New, entity1Created, entity1Found;

        // preparation
        entity1New = new Reminder();
       
  
        entity1New.setStatus(ReminderStatus.ACTIVE);
        entity1Created = reminderManager.create(entity1New);
        
        // execution
        entity1Found = reminderManager.findById(entity1Created.getId());
        
        // evaluation
        assertNotNull(entity1Found);
        assertEquals(entity1Created.getId(), entity1Found.getId());
        assertEquals(ReminderStatus.ACTIVE, entity1Found.getStatus());
    }

    /**
     * Test of findAll method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testFindAll() {
        Reminder reminderA, reminderB;
        List<Reminder> reminderFoundBefore, reminderFoundAfter;

        // preparation
        reminderFoundBefore = reminderManager.findAll();
        reminderA = new Reminder();
        reminderB = new Reminder();
        reminderManager.create(reminderA);
        reminderManager.create(reminderB);
        
        // execution
        reminderFoundAfter = reminderManager.findAll();
        
        // evaluation
        assertEquals(2, reminderFoundAfter.size() - reminderFoundBefore.size());
    }
	
}
