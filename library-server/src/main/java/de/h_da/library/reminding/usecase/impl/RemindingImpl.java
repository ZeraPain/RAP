package de.h_da.library.reminding.usecase.impl;



import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.h_da.library.accounting.entity.Invoice;
import de.h_da.library.accounting.manager.InvoiceManager;
import de.h_da.library.datamanagement.entity.Customer;
import de.h_da.library.datamanagement.entity.Loan;
import de.h_da.library.datamanagement.entity.Reminder;
import de.h_da.library.datamanagement.manager.LoanManager;
import de.h_da.library.datamanagement.manager.ReminderManager;
import de.h_da.library.datamanagement.type.ReminderStatus;
import de.h_da.library.reminding.usecase.Reminding;
import de.h_da.library.reminding.usecase.RemindingRemote;

@Stateless
public class RemindingImpl implements Reminding, RemindingRemote {
	
	@EJB
	LoanManager loanManager;
	@EJB
	ReminderManager reminderManager;
	@EJB
	InvoiceManager invoiceManager;
   

	@Override
	public void sendReminders() {
		// TODO Auto-generated method stub
		List<Loan> loans = loanManager.findAll();
		List<Reminder> reminders = reminderManager.findAll();
		List<Invoice> invoices = invoiceManager.findAll();
				
		Date now = new Date();
		
        List<Loan> overduedLoans = loans.stream()               
                .filter(loan -> now.after(loan.getDueDate()))
                .collect(Collectors.toList()); 
        
        for (int i = 0; i < overduedLoans.size(); i++) {
	  	  	Loan loan = overduedLoans.get(i);
	  	  	Customer customer =  loan.getCustomer();
	  	  
	      	for (int x = 0; x < reminders.size(); x++) {
	  		  Reminder reminder = reminders.get(x);
	      	  
	      	  if(loan.equals(reminder.getLoan())) {
	      		  //loan has already existing reminder
	      		  
	      	  }
	      	  else {
	      		//create invoice
     			 Invoice newInvoice = new Invoice();
     			 newInvoice.setDate(now);
     			 newInvoice.setSubject("Library Reminder");
     			 newInvoice.setAmount(300);
     			 newInvoice.setAddress(customer.getAddress());
     			 newInvoice.setName(customer.getName());
     			
     			 
     			Invoice createdInvoice = invoiceManager.create(newInvoice);
     			
     			//check if invoice created correctly            			
     			
     			
     			// create Reminder
     	        LocalDateTime localDateTime = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
     	        localDateTime = localDateTime.plusDays(20);
     	        Date currentDatePlus= Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
     	        
     	        Reminder newReminder = new Reminder();
     			newReminder.setFee(300L);
     			
     			newReminder.setIssueDate(now);
     			newReminder.setDueDate(currentDatePlus);
     			newReminder.setInvoiceId(createdInvoice.getId().toString());
     			newReminder.setStatus(ReminderStatus.ACTIVE);
     			
     			Reminder reminderCreated = reminderManager.create(newReminder);
     			
     			// check if Reminder created correctly
	      	  }
	      	  
	        }
      	}
        

              
		
		
	}

	@Override
	public void closeReminder(Long invoiceId) {
		// TODO Auto-generated method stub
		List<Reminder> reminders = reminderManager.findAll();
		
		
		for (int i = 0; i < reminders.size(); i++) {
  		  Reminder reminder = reminders.get(i);
  		  
  		  if(reminder.getInvoiceId().equals(invoiceId)) {
  			  if(reminder.getStatus() == ReminderStatus.ACTIVE) {
  				  reminder.setStatus(ReminderStatus.CLOSED);
  				  reminderManager.edit(reminder);
  				// check if Reminder edited correctly
  			  }
  		  }

		}
	}
	
}
