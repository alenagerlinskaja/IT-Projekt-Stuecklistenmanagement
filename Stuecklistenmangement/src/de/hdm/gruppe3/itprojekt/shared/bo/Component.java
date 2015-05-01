package de.hdm.gruppe3.itprojekt.shared.bo;

import java.util.Date;

public class Component extends Element{


	private static final long serialVersionUID = 1L;
	
	/**
	 * Datum der letzten Änderung eines Users
	 */
	
	private Date dateOfModification;
	
	/** 
	 * Auslesen des Änderungsdatums
	 */
	
	public Date getDateOfModification() {
		return dateOfModification;
	}

	/** 
	 * Setzen des Änderungsdatums
	 */
	public void setDateOfModification(Date dateOfModification) {
		this.dateOfModification = dateOfModification;
	} 
	
}
