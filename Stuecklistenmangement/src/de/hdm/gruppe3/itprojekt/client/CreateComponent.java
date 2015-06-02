package de.hdm.gruppe3.itprojekt.client;


/** 
 * In diesem Showcase wird eine neue Baugruppe mit der Nummer 1 erstellt. 
 * @author Teuta
 */

public class CreateComponent extends Showcase {
	
/**
 * Jeder Showcase besitzt eine Überschrift. Diese wird mit folgender Methode gesetzt.
 *
 */
protected String getHeadlineText() {
	return "Baugruppe anlegen";
}

/** In jedem Showcase muss die run-Methode implementiert werden.
 * Ist der Showcase aktiviert, so wird diese Methode von einer Methode der Basisklasse Showcase aufgerufen. 
 */

protected void run() {
	//Hier wird angekündigt was nun geschehen soll
    this.append ("Anlegen einer neuen Baugruppe mit der Nummer 1");
}

}
