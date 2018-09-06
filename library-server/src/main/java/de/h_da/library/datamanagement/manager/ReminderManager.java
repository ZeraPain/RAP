/*
 * ReminderManager.java
 *
 *
 */

package de.h_da.library.datamanagement.manager;

import java.util.List;

import javax.ejb.Local;

import de.h_da.library.datamanagement.entity.Reminder;


/**
 *
 * @author dcal
 */
@Local
public interface ReminderManager {

	    void edit(Reminder reminder);

	    void destroy(Reminder reminder);

	    Reminder findById(Long id);

	    List<Reminder> findAll();

	    Reminder create(Reminder reminder);
	    
}
