/*
 * Book.java
 *
 * Created on 18. September 2007, 11:17
 *
 * TODO: delete and start from scratch
 */

package de.h_da.library.datamanagement.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book implements Serializable{
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String authors;
    private String title;
    
    public Book() {}
    
    
    /**
     * Gets the authors of this Book.
     * @return the authors
     */
	public String getAuthors() {
		return authors;
	}
	
    /**
     * Sets the authors of this Book.
     * @param the authors
     */
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	
    /**
     * Gets the title of this Book.
     * @return the title
     */
	public String getTitle() {
		return title;
	}
	
    /**
     * Sets the title of this Book.
     * @param the title
     */
	public void setTitle(String title) {
		this.title = title;
	}
    
    
}
