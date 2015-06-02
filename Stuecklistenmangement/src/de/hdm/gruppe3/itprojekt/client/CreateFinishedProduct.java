package de.hdm.gruppe3.itprojekt.client;

/** 
 * In diesem Showcase wird eine neues Erzeugnis mit der Nummer 1 erstellt. 
 * @author Teuta
 */

public class CreateFinishedProduct extends Showcase {
	
/**
 * Jeder Showcase besitzt eine Überschrift. Diese wird mit folgender Methode gesetzt.
 *
 */
protected String getHeadlineText() {
	return "Erzeugnis anlegen";
}

/** In jedem Showcase muss die run-Methode implementiert werden.
 * Ist der Showcase aktiviert, so wird diese Methode von einer Methode der Basisklasse Showcase aufgerufen. 
 */

protected void run() {
	//Hier wird angekündigt was nun geschehen soll
    this.append ("Anlegen eines neuen Erzeugnnisses mit der Nummer 1");
}

}

