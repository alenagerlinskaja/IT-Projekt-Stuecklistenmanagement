package de.hdm.gruppe3.itprojekt.shared.bo;

public class User extends BusinessObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Der Vorname des Nutzers.
	 */
	
	private String firstName;
	
	/**
	 * Der Nachname des Nutzers.
	 */
	private String lastName;
	
	/**
	 * Die E-Mail-Adresse des Nutzers.
	 */
	private String email;
	
	/**
	 * Die Google-ID des Nutzers.
	 */
	private String googleId;
	
	/**
	 * Das Passwort des Nutzers.
	 */
	private String password;
	
	
	/**
	 * Auslesen des Vornamens
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Setzen des Vornamens.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Auslesen des Nachnamens.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Setzen des Nachnamens.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Auslesen der E-Mail-Adresse.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Setzen der E-Mail-Adresse.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Auslesen der Google-Id.
	 */
	public String getGoogleId() {
		return googleId;
	}
	/**
	 * Setzen der Google-Id.
	 */
	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}
	/**
	 * Auslesen des Passworts.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Setzen des Passworts.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
