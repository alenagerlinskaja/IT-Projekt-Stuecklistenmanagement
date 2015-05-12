package de.hdm.gruppe3.itprojekt.shared.report;

import java.io.Serializable;


/**
 * Dies iste die Spalte eines Row-Objekts. Column-Objekte
 * implementieren das Serializable-Interface und können daher als
 * Kopie beispielsweise vom Server an den Client Übertragen werden.
 */

public class Column implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String value = "";
	
	private Column() {
		
	}
	
	public Column (String s) {
			this.value = s;
		}
	
		
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value=value;
	}
	public String toString(){
		return this.value;
	}
	
}
