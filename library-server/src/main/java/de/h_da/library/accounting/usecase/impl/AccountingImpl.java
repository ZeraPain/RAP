package de.h_da.library.accounting.usecase.impl;

import de.h_da.library.accounting.usecase.Accounting;
import de.h_da.library.accounting.usecase.AccountingRemote;

import javax.ejb.EJB;

import java.util.Calendar;
import java.util.Date;

import de.h_da.library.accounting.entity.Invoice;

import de.h_da.library.accounting.manager.InvoiceManager;
import de.h_da.library.accounting.type.InvoiceStatus;

public class AccountingImpl implements Accounting, AccountingRemote {
	@EJB
	InvoiceManager invoiceManager;
	
	/**
	 * Generates a new <code>Invoice</code> object.
	 * 
	 * @param invoiceSubject
	 *            [in] invoice subject
	 * @param invoiceName
	 *            [in] name of the invoice's recipient
	 * @param invoiceAddress
	 *            [in] address where the invoice is to be sent to
	 * @param invoiceAmount
	 *            [in] invoice amount in Euro cents
	 * @return [out] id of the generated <code>Invoice</code> object
	 * 
	 * <pre>
	 *       [post exists new Invoice i: i.id == result 
	 *        post i.date is current date
	 *        post i.subject == invoiceSubject
	 *        post i.name == invoiceName
	 *        post i.address == invoiceAddress
	 *        post i.amount == invoiceAmount
	 *        post i.status == InvoiceStatus.SENT
	 *       ]
	 * </pre>
	 */
	@Override
	public Long sendInvoice(String invoiceSubject, String invoiceName, String invoiceAddress,
			int invoiceAmount) {
		Invoice invoice = new Invoice();
		invoice.setSubject(invoiceSubject);
		invoice.setName(invoiceName);
		invoice.setAddress(invoiceAddress);
		invoice.setAmount(invoiceAmount);
		invoice.setDate(new Date());
		invoice.setStatus(InvoiceStatus.SENT);
		
		Invoice invoiceCreated = invoiceManager.create(invoice);
		
		if (invoiceCreated == null) {
			//throw ERROR
		}
		
		if (true) {
			
		}
		
		return new Long(0);
	}

	/**
	 * Notifies the accounting component that the money corresponding to an
	 * invoice has been received and, hence, the invoice can be closed
	 * 
	 * @param invoiceId
	 *            [in] the id of the invoice to be closed
	 * 
	 * <pre>
	 *     [post Invoice i (i.id == invoiceID): i.invoiceStatus == InvoiceStatus.PAID
	 * </pre>
	 */
	@Override
	public void receiveMoney(Long invoiceId) {
		
	}

}