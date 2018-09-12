package de.h_da.library.accounting.usecase.impl;


import java.util.Collection;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sun.mail.imap.protocol.Status;

import de.h_da.library.accounting.manager.InvoiceManager;
import de.h_da.library.accounting.usecase.Accounting;
import de.h_da.library.accounting.usecase.AccountingRemote;
import de.h_da.library.datamanagement.entity.Loan;
import de.h_da.library.reminding.usecase.Reminding;
import de.h_da.library.accounting.entity.Invoice;
import de.h_da.library.accounting.manager.InvoiceManager;
import de.h_da.library.accounting.type.InvoiceStatus;




public class AccountingImpl implements Accounting, AccountingRemote {

	@EJB
	InvoiceManager invoiceManager;
	
	
		@Override
		public Long sendInvoice(String invoiceSubject, String invoiceName, String invoiceAddress, int invoiceAmount) {
			 
			Date date = new Date();
			
			
		
			Invoice invoice = new Invoice();
			invoice.setName(invoiceName);
			invoice.setAmount(invoiceAmount);
			invoice.setSubject(invoiceSubject);
			invoice.setAddress(invoiceAddress);
			invoice.setDate(date);
			invoice.setStatus(InvoiceStatus.SENT);
			
		
			Invoice invoiceCreated = invoiceManager.create(invoice);
			if (invoiceCreated == null) {
				//throw ERROR
			}
				
			return invoiceCreated.getId();
			
		}

		
		@Override
		public void receiveMoney(Long invoiceId) {
			 
			Invoice invoice = invoiceManager.findById(invoiceId);
			invoice.setStatus(InvoiceStatus.PAID);
			
			
		}

	
}

