/*
 * Customer.java
 *
 * Created on 18. September 2007, 11:18
 *
 * TODO: delete and start from scratch
 */

package de.h_da.library.datamanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  String name;
  String address;
  
  
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    
}
