package de.h_da.library.datamanagement.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BookOnStock implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade={CascadeType.MERGE}, fetch=FetchType.EAGER)
	private Book book;
	
	public BookOnStock() {}

	public Long getId() {
		return id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
