package de.hdm.gruppe3.itprojekt.shared.bo;

import java.util.Date;

public class BillOfMaterial extends BusinessObject{


	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private Date creationDate;
	
	/**
	 * Auslesen des Namens der Stückliste
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * Setzen des Namens der Stückliste
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Auslesen des Erstellungsdatums
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	
	/**
	 * Setzen des Erstellungsdatums
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	} 
	
}
