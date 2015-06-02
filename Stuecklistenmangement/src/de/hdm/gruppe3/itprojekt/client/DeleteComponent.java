package de.hdm.gruppe3.itprojekt.client;

import de.hdm.thies.bankProjekt.client.ClientsideSettings;
import de.hdm.thies.bankProjekt.client.DeleteCustomerDemo.DeleteCustomerCallback;
import de.hdm.thies.bankProjekt.shared.BankAdministrationAsync;

/** 
 * In diesem Showcase wird das Löschen eines Components mit der Nummer 3 dargestellt.
 * @author Teuta
 *
 */




public class DeleteComponent extends Showcase {
	
	/**
	 *  Da jeder Showcase eine einleitende Überschrif besitzt, muss auch dieser Showcase eine enthalten
	 */
	protected String getHeadlineText() {
		return "Delete Component";
	}
	/**
	 * Die run()-Methode muss in jedem Showcase implementiert sein. Diese wird von einer 
	 * Methode der Basisklasse Showcase aufgerufen, sobald diese aktiviert wird.
	 */
	
	protected void run() {
		this.append("Löschen des Components mit der Nummer 3.");
		
		
	}
	




}
