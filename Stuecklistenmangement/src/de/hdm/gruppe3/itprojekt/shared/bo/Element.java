package de.hdm.gruppe3.itprojekt.shared.bo;

public class Element extends BusinessObject {

	
	private static final long serialVersionUID = 1L;
	/** Name des Elements
	 */
	private String name;
	
	
	/**Auslesen des Namens
	 */
	public String getName() {
		return name;
	}

	
	/**Setzen des Namens
	 */
	public void setName(String name) {
		this.name = name;
	}	

}
