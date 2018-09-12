package de.h_da.library.datamanagement.usecase;

import javax.ejb.*;

/**
 * [usecase] This use case comprises all functionality used by library staff to
 * administer the Reminder base.
 * 
 */
@Remote
public interface ReminderManagementRemote {


	public void sendReminders();


	public void closeReminder(long invoiceId);


}
