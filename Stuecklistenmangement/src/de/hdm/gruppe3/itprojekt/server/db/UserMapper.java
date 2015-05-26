package de.hdm.gruppe3.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.gruppe3.itprojekt.shared.bo.*;

/**
 * Mapper-Klasse, die <code>User</code>-Objekte auf eine relationale Datenbank
 * abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung gestellt, mit
 * deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und gelöscht werden
 * können. Das Mapping ist bidirektional. D.h., Objekte können in DB-Strukturen
 * und DB-Strukturen in Objekte umgewandelt werden.
 * <p>
 * 
 * @author Thies
 */

public class UserMapper {
	/**
	 * Die Klasse UserMapper wird nur einmal instantiiert. Man spricht hierbei
	 * von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 */

	private static UserMapper userMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit new neue
	 * Instanzen dieser Klasse zu erzeugen.
	 * 
	 */
	protected UserMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>UserMapper.userMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>UserMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> UserMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>UserMapper</code>-Objekt.
	 * @see userMapper
	 */

	public static UserMapper userMapper() {
		if (userMapper == null) {
			userMapper = new UserMapper();
		}

		return userMapper;
	}

	/**
	 * Einfügen eines <code>User</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param u
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */
	public User insert(User u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM User ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * u erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				u.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO User (id, firstName, lastName, email, googleId, password) "
						+ "VALUES ("
						+ u.getId()
						+ ",'"
						+ u.getFirstName()
						+ "','"
						+ u.getLastName()
						+ "','"
						+ u.getEmail()
						+ "','"
						+ u.getGoogleId()
						+ "','"
						+ u.getPassword()
						+ "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		 * Rückgabe, des evtl. korrigierten Users.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte übergeben werden, wäre die Anpassung des User-Objekts auch
		 * ohne diese explizite Rückgabe außerhalb dieser Methode sichtbar. Die
		 * explizite Rückgabe von u ist eher ein Stilmittel, um zu
		 * signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * verändert hat.
		 */
		return u;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param u
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter Ãübergebene Objekt
	 */
	public User update(User u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE User " + "SET firstName=\""
					+ u.getFirstName() + "\", " + "lastName=\""
					+ u.getLastName() + "\", " + "email=\"" + u.getEmail()
					+ "\", " + "googleId=\"" + u.getGoogleId() + "\", "
					+ "password=\"" + u.getPassword() + "\" " + "WHERE id="
					+ u.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Um Analogie zu insert(User u) zu wahren, geben wir u zurück
		return u;
	}

	/**
	 * Löschen der Daten eines <code>User</code>-Objekts aus der Datenbank.
	 * 
	 * @param u
	 *            das aus der DB zu löschende "Objekt"
	 */
	public void delete(User u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM User " + "WHERE id=" + u.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Suchen eines Nutzers mit vorgegebener Id. Da diese eindeutig ist, wird
	 * genau ein Objekt zurückgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return Nutzer-Objekt, das dem übergebenen Schlüssel entspricht, null bei
	 *         nicht vorhandenem DB-Tupel.
	 */
	public User findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt
					.executeQuery("SELECT id, firstName, lastName, email, googleId, password FROM User "
							+ "WHERE id=" + id + " ORDER BY lastName");

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
			 * werden. Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setFirstName(rs.getString("firstName"));
				u.setLastName(rs.getString("lastName"));
				u.setEmail(rs.getString("email"));
				u.setGoogleId(rs.getString("googleId"));
				u.setPassword(rs.getString("password"));

				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Auslesen aller Nutzer.
	 * 
	 * @return Ein Vektor mit User-Objekten, die sämtliche Nutzer
	 *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
	 *         oder ggf. auch leerer Vetor zurückgeliefert.
	 */
	public Vector<User> findAll() {
		Connection con = DBConnection.connection();
		// Ergebnisvektor vorbereiten
		Vector<User> result = new Vector<User>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, firstName, lastName, email, googleId, password "
							+ "FROM User " + "ORDER BY lastName");

			// Für jeden Eintrag im Suchergebnis wird nun ein User-Objekt
			// erstellt.
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setFirstName(rs.getString("firstName"));
				u.setLastName(rs.getString("lastName"));
				u.setEmail(rs.getString("email"));
				u.setGoogleId(rs.getString("googleId"));
				u.setPassword(rs.getString("password"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * Auslesen aller Nutzer-Objekte mit gegebenem Nachnamen
	 * 
	 * @param name
	 *            Nachname der Nutzer, die ausgegeben werden sollen
	 * @return Ein Vektor mit User-Objekten, die sämtliche Nutzer mit dem
	 *         gesuchten Nachnamen repräsentieren. Bei evtl. Exceptions wird ein
	 *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
	 */
	public Vector<User> findByLastName(String name) {
		Connection con = DBConnection.connection();
		Vector<User> result = new Vector<User>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, firstName, lastName, email, googleId, password "
							+ "FROM User "
							+ "WHERE lastName LIKE '"
							+ name
							+ "' ORDER BY lastName");

			// Für jeden Eintrag im Suchergebnis wird nun ein User-Objekt
			// erstellt.
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setFirstName(rs.getString("firstName"));
				u.setLastName(rs.getString("lastName"));
				u.setEmail(rs.getString("email"));
				u.setGoogleId(rs.getString("googleId"));
				u.setPassword(rs.getString("password"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zurÃ¼ckgeben
		return result;
	}

}
