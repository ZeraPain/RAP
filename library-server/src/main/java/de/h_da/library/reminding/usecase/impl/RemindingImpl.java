package de.h_da.library.reminding.usecase.impl;



import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import de.h_da.library.GlobalInterceptor;
import de.h_da.library.LibraryException;
import de.h_da.library.LibraryRuntimeException;
import de.h_da.library.accounting.entity.Invoice;
import de.h_da.library.accounting.manager.InvoiceManager;
import de.h_da.library.datamanagement.entity.Customer;
import de.h_da.library.datamanagement.entity.Loan;
import de.h_da.library.datamanagement.entity.Reminder;
import de.h_da.library.datamanagement.manager.LoanManager;
import de.h_da.library.datamanagement.manager.ReminderManager;
import de.h_da.library.datamanagement.type.LoanStatus;
import de.h_da.library.datamanagement.type.ReminderStatus;
import de.h_da.library.reminding.usecase.Reminding;
import de.h_da.library.reminding.usecase.RemindingRemote;

@Stateless
@Interceptors(GlobalInterceptor.class)
public class RemindingImpl implements Reminding, RemindingRemote {
	
	@EJB
	LoanManager loanManager;
	@EJB
	ReminderManager reminderManager;
	@EJB
	InvoiceManager invoiceManager;
   

	@Override
	public void sendReminders() throws LibraryException {
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
     			if (reminderCreated.getLoan().getStatus() == LoanStatus.DELAYED) {
     				throw new LibraryRuntimeException("Could not create reminder");
     			}
     			
     			if (reminderCreated.getFee() != 300L) {
     				throw new LibraryRuntimeException("Reminder fee unequals returned");
     			}
     			
     			if (!createdInvoice.getId().toString().equals(reminderCreated.getInvoiceId())) {
     				throw new LibraryException("Created Reminder Invoice not equal to referring incoive id ");
     			}
     			
     			
     			
     			if (reminderCreated.getStatus() != ReminderStatus.ACTIVE) {
     				try {
     					throw new LibraryException("Reminder status unequals returned");
     				} catch (LibraryException e) {
     					// TODO Auto-generated catch block
     					e.printStackTrace();
     				}
     			}
     			
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
