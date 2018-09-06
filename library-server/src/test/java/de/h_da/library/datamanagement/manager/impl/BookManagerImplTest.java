package de.h_da.library.datamanagement.manager.impl;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import de.h_da.library.datamanagement.manager.BookManager;

import de.h_da.library.configuration.LibraryTest;

@RunWith(Arquillian.class)
public class BookManagerImplTest extends LibraryTest{

	@EJB
	private BookManager bookManager;
	
	
}
