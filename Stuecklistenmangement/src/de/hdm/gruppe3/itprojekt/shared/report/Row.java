package de.hdm.gruppe3.itprojekt.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * Zeile einer Tabelle eines <code>SimpleReport</code>-Objekts. <code>Row</code>
 * -Objekte implementieren das <code>Serializable</code>-Interface und kÃ¶nnen
 * daher als Kopie z.B. vom Server an den Client Ã¼bertragen werden.
 * 
 * @see SimpleReport
 * @see Column
 * @author Aliye Gököz/ In Anlehnung an Herr Prof. Thies
 */
public class Row implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Speicherplatz für die Spalten der Zeile.
	 */
	private Vector<Column> columns = new Vector<Column>();
	
	/**
	 * Hinzüfügen einer Spalte .
	 * 
	 * @param c des Spaltenobjekt
	 */
	public void addColumn(Column c) {
		this.columns.addElement(c);
	}
	
	/**
	 * Entfernen einer Spalte.
	 * 
	 * @param c das zu entfernende Spaltenobjekt
	 */
	public void removeColumn(Column c) {
		this.columns.removeElement(c);
	}
	
	/**
	 * Auslesen sämtlicher Spalten
	 * 
	 * @return <code>Vector</code>-Objekts mit sÃ¤mtlichen Spalten
	 */
	public Vector<Column> getColumns() {
		return this.columns;
	}
	
	/**
	 * Auslesen der Anzahl sämtlicher Spalten.
	 * 
	 * @return int Anzahl der Spalten
	 */
	public int getNumColumns() {
		return this.columns.size();
	}
	
	/**
	 * Auslesen eines einzelnen Spalten-Objekts.
	 * 
	 * @param i der Index der auszulesenden Spalte (0 <= i < n), mit n = Anzahl
	 *          der Spalten.
	 * @return das gewÃ¼nschte Spaltenobjekt.
	 */
	public Column getColumnAt(int i) {
		return this.columns.elementAt(i);
	}
	
	
	

}
