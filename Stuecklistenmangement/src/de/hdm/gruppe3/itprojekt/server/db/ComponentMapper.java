package de.hdm.gruppe3.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.gruppe3.itprojekt.shared.bo.BillOfMaterial;
import de.hdm.gruppe3.itprojekt.shared.bo.Component;
import de.hdm.gruppe3.itprojekt.shared.bo.ComponentPart;
import de.hdm.gruppe3.itprojekt.shared.bo.FinishedProduct;

/**
 * Mapper-Klasse, die <code>Component</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * <p>
 * @author Thies
 */
public class ComponentMapper {

	/**
	 * Die Klasse ComponentMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 */

	private static ComponentMapper componentMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit new neue
	 * Instanzen dieser Klasse zu erzeugen.
	 * 
	 */

	protected ComponentMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>ComponentMapper.componentMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>ComponentMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> ComponentMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>ComponentMapper</code>-Objekt.
	 */

	public static ComponentMapper componentMapper() {
		if (componentMapper == null) {
			componentMapper = new ComponentMapper();
		}

		return componentMapper;
	}

	/**
	 * Einfügen eines <code>Component</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param c
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */

	public Component insert(Component c) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM Component ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {

				/*
				 * c erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				c.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO Component (id, name, description, material, dateOfModification) "
						+ "VALUES ("
						+ c.getId()
						+ ","
						+ c.getName()
						+ ","
						+ c.getDescription()
						+ ","
						+ c.getMaterial()
						+ ","
						+ c.getDateOfModification() + "," + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		/*
		 * Rückgabe, der evtl. korrigierten Baugruppe.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte übergeben werden, wäre die Anpassung des Component-Objekts
		 * auch ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar.
		 * Die explizite Rückgabe von c ist eher ein Stilmittel, um zu
		 * signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * verändert hat.
		 */
		return c;

	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param c
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */
	public Component update(Component c) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE Component " + "SET name=\""
					+ c.getName() + "description=\"" + c.getDescription()
					+ "\", " + "material=\"" + c.getMaterial() + "\", "
					+ "dateOfModification=\"" + c.getDateOfModification()
					+ "\" " + "WHERE id=" + c.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Um Analogie zu insert(Component c) zu wahren, geben wir c zurück
		return c;

	}

	/**
	 * Löschen der Daten eines <code>Component</code>-Objekts aus der Datenbank.
	 * 
	 * @param c
	 *            das aus der DB zu löschende "Objekt"
	 */
	public Component delete(Component c) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM Component " + "WHERE id="
					+ c.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Suchen einer Baugruppe mit vorgegebener Id. Da diese eindeutig ist, wird
	 * genau ein Objekt zur�ckgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return Baugruppen-Objekt, das dem übergebenen Schlüssel entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 */

	public Component findById(int id) {

		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt
					.executeQuery("SELECT id, name, description, material, dateOfModification FROM Component "
							+ "WHERE id=" + id + " ORDER BY name");

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
			 * werden. Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Component c = new Component();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setDescription(rs.getString("description"));
				c.setMaterial(rs.getString("material"));
				c.setDateOfModification(rs.getDate("dateOfModification"));

				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Auslesen aller Baugruppen.
	 * 
	 * @return Ein Vektor mit Component-Objekten, die sämtliche Kunden
	 *         repräsentieren. Bei evtl. Exceptions wird ein partiell gef�llter
	 *         oder ggf. auch leerer Vetor zurückgeliefert.
	 */

	public Vector<Component> findByAll() {

		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Component> result = new Vector<Component>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, description, material, dateOfModification "
							+ "FROM Component " + "ORDER BY name");

			// Für jeden Eintrag im Suchergebnis wird nun ein Component-Objekt
			// erstellt.
			while (rs.next()) {
				Component c = new Component();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setDescription(rs.getString("description"));
				c.setMaterial(rs.getString("material"));
				c.setDateOfModification(rs.getDate("dateOfModification"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	// (find by Bom), hier muss man in der Klammer den Fremdschl��ssel
	// einf��gen, nicht (int id)
	public Vector<Component> findByComponentPart(int id) {

	}

	public Vector<Component> findByComponentPart(ComponentPart p) {

	}

	public Vector<Component> findByFinishedProduct(int id) {

	}

	public Vector<Component> findByFinishedProduct(FinishedProduct f) {

	}

	public BillOfMaterial getBomOf(Component c) {

	}

}
