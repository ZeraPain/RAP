package de.h_da.library.datamanagement.usecase;

import javax.ejb.*;

/**
 * [usecase] This use case comprises all functionality used by library staff to
 * administer the Reminder base.
 * 
 */
@Local
public interface ReminderManagment {



	public void sendReminders();


	public void closeReminder(long invoiceId);

	
	

}
