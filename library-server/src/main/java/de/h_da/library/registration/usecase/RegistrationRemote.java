package de.h_da.library.registration.usecase;

import javax.ejb.Remote;

import de.h_da.library.LibraryException;
import de.h_da.library.RegistrationException;
import de.h_da.library.datamanagement.entity.Customer;

@Remote
public interface RegistrationRemote {
	/**
	 * [command] Registers a new customer and generates an invoice for the
	 * registration fee.
	 * 
	 * @param customer
	 *            [inout] a volatile <code>Customer</code> object
	 * @return [out] the id of the persistent <code>Customer</code> object
	 * 
	 * <pre>
	 *        [pre not exists Customer c: c.id == result
	 *         post exists Customer c: c.id == result
	 *         post c.name.equals(customer.name)
	 *         analogous for all other attributes of entity Customer
	 *         post exists Invoice i: 
	 *              i.date is current date 
	 *              &amp;&amp; i.subject.equals(&quot;Registration fee&quot;) 
	 *              &amp;&amp; i.name.equals(customer.name) 
	 *              &amp;&amp; i.address.equals(customer.address)
	 *              &amp;&amp; i.amount = 500 &amp;&amp; // EUR 5.00 registration fee
	 *        ]
	 * </pre>
	 */
	public Long register(Customer customer) throws RegistrationException;

	/**
	 * Modifies the data of an already registered customer
	 * 
	 * @param customer
	 *            a volatile <code>Customer</code> object with id
	 * 
	 * <pre>
	 *        [pre exists Customer c: c.id == customer.id
	 *         post c.name.equals(customer.name)
	 *         analogous for all other attributes of entity Customer
	 *        ]
	 * </pre>
	 * 
	 */
	public void modifyRestistration(Customer customer) throws RegistrationException;
	
	public Customer findCustomerById(Long id) throws LibraryException;
}
