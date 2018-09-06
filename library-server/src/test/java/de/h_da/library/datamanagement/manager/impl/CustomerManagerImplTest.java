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
import de.h_da.library.datamanagement.entity.Customer;
import de.h_da.library.datamanagement.manager.CustomerManager;

@RunWith(Arquillian.class)
public class CustomerManagerImplTest extends LibraryTest{


	@EJB
	private CustomerManager customerManager;
	
    @Test
    public void testCreate() {
        Customer customer, customerCreated;

        // preparation
        customer = new Customer();
        customer.setName("att1");
        customer.setAddress("Title");

        // execution
        customerCreated = customerManager.create(customer);
        
        // evaluation
        assertNotNull(customerCreated);
        assertNotNull(customerCreated.getId());
        assertEquals("att1", customerCreated.getName());
        assertEquals("Title", customerCreated.getAddress());
    }

    /**
     * Test of edit method, of class de.h_da.library.component1.manager.impl.CustomerManagerImpl.
     */
    @Test
    public void testEdit() {
        Customer customerNew, customerCreated, customerFound;

        // preparation
        customerNew = new Customer();
        customerNew.setName("attOld");
        customerNew.setAddress("Title");
        customerCreated = customerManager.create(customerNew);
        customerCreated.setName("attNew");
        customerCreated.setAddress("Title");
        
        // execution
        customerManager.edit(customerCreated);
        
        // evaluation
        customerFound = customerManager.findById(customerCreated.getId());
        assertEquals("attNew", customerFound.getName());
        assertEquals("Title", customerFound.getAddress());
    }

    /**
     * Test of destroy method, of class de.h_da.library.component1.manager.impl.CustomerManagerImpl.
     */
    @Test
    public void testDestroy() {
        Customer newCustomerRecord, customerCreated, customerFound;

        // preparation
        newCustomerRecord = new Customer();
        customerCreated = customerManager.create(newCustomerRecord);
        
        // execution
        customerManager.destroy(customerCreated);

        // evaluation
        customerFound = customerManager.findById(customerCreated.getId());
        assertNull(customerFound);
    }

    /**
     * Test of findById method, of class de.h_da.library.component1.manager.impl.CustomerManagerImpl.
     */
    @Test
    public void testFindById() {
        Customer customerNew, customerCreated, customerFound;

        // preparation
        customerNew = new Customer();
        customerNew.setName("att1");
        customerNew.setAddress("Title");
        customerCreated = customerManager.create(customerNew);
        
        // execution
        customerFound = customerManager.findById(customerCreated.getId());
        
        // evaluation
        assertNotNull(customerFound);
        assertEquals(customerCreated.getId(), customerFound.getId());
        assertEquals("att1", customerFound.getName());
        assertEquals("Title", customerFound.getAddress());
    }

    /**
     * Test of findAll method, of class de.h_da.library.component1.manager.impl.CustomerManagerImpl.
     */
    @Test
    public void testFindAll() {
        Customer customerNewA, customerNewB;
        List<Customer> entities1FoundBefore, entities1FoundAfter;

        // preparation
        entities1FoundBefore = customerManager.findAll();
        customerNewA = new Customer();
        customerNewB = new Customer();
        customerManager.create(customerNewA);
        customerManager.create(customerNewB);
        
        // execution
        entities1FoundAfter = customerManager.findAll();
        
        // evaluation
        assertEquals(2, entities1FoundAfter.size() - entities1FoundBefore.size());
    }
}