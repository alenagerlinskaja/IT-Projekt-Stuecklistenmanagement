package de.hdm.gruppe3.itprojekt.shared.bo;

public class ComponentPart extends BusinessObject {

	private static final long serialVersionUID = 1L;
	/**
	 * Name des Elements
	 */
	private String name;

	/**
	 * Auslesen des Namens
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzen des Namens
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Textuelle Beschreibung
	 */
	private String description = "";

	/**
	 * Materialbezeichnung
	 */
	private String material = "";

	/**
	 * Auslesen der Textuellen Beschreibung
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Setzen der Textuellen Beschreibung
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Auslesen der Materialbezeichnung
	 */
	public String getMaterial() {
		return this.material;
	}

	/**
	 * Setzen der Materialbezeichnung
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * Fremdschlüsselbeziehung zu Component.
	 */

	private int cID;

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}

}
