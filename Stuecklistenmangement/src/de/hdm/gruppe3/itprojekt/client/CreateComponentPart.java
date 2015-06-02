package de.hdm.gruppe3.itprojekt.client;

/** 
 * In diesem Showcase wird eine neues Bauteil mit der Nummer 1 erstellt. 
 * @author Teuta
 */

public class CreateComponentPart extends Showcase {
	
/**
 * Jeder Showcase besitzt eine Überschrift. Diese wird mit folgender Methode gesetzt.
 *
 */
protected String getHeadlineText() {
	return "Bauteil anlegen";
}

/** In jedem Showcase muss die run-Methode implementiert werden.
 * Ist der Showcase aktiviert, so wird diese Methode von einer Methode der Basisklasse Showcase aufgerufen. 
 */

protected void run() {
	//Hier wird angekündigt was nun geschehen soll
    this.append ("Anlegen eines neuen Bauteils mit der Nummer 1");
}

}
