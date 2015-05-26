package de.hdm.gruppe3.itprojekt.server.db;


import java.sql.*;
import java.util.Vector;

import de.hdm.gruppe3.itprojekt.shared.bo.ComponentPart;

/**
 * Mapper-Klasse, die <code>ComponentPart</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * <p>
 * @author Thies
 */

public class ComponentPartMapper {

	/**
	 * Die Klasse ComponentPartMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 */

	private static ComponentPartMapper componentPartMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit new neue
	 * Instanzen dieser Klasse zu erzeugen.
	 * 
	 */

	protected ComponentPartMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>ComponentPartMapper.componentPartMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>ComponentPartMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> ComponentPartMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>ComponentPartMapper</code>-Objekt.
	 */

	public static ComponentPartMapper componentPartMapper() {
		if (componentPartMapper == null) {
			componentPartMapper = new ComponentPartMapper();
		}

		return componentPartMapper;
	}

	/**
	 * Einfügen eines <code>ComponentPart</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param p 
	 *              das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */

	public ComponentPart insert(ComponentPart p) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM ComponentPart ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {

				/*
				 * p erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				p.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO ComponentPart (id, name, description, material, dateOfModification, cID) "
						+ "VALUES ("
						+ p.getId()
						+ ","
						+ p.getName()
						+ ","
						+ p.getDescription()
						+ ","
						+ p.getMaterial()
						+ ","
						+ p.getDateOfModification() 
						+ ","
						+ p.getcID() 
						+ ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		/*
		 * Rückgabe, des evtl. korrigierten Bauteils.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte übergeben werden, wäre die Anpassung des ComponentPart-Objekts
		 * auch ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar.
		 * Die explizite Rückgabe von p ist eher ein Stilmittel, um zu
		 * signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * verändert hat.
		 */
		return p;

	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param p
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */
	public ComponentPart update(ComponentPart p) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE ComponentPart " + "SET name=\""
					+ p.getName() + "description=\"" + p.getDescription()
					+ "\", " + "material=\"" + p.getMaterial() + "\", "
					+ "dateOfModification=\"" + p.getDateOfModification() + "cID=\"" + p.getcID()
					+ "\" " + "WHERE id=" + p.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Um Analogie zu insert(ComponentPart p) zu wahren, geben wir p zurück
		return p;

	}

	/**
	 * Löschen der Daten eines <code>ComponentPart</code>-Objekts aus der Datenbank.
	 * 
	 * @param p
	 *            das aus der DB zu löschende "Objekt"
	 */
	public ComponentPart delete(ComponentPart p) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM ComponentPart " + "WHERE id="
					+ p.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Suchen eines Bauteils mit vorgegebener Id. Da diese eindeutig ist, wird
	 * genau ein Objekt zur�ckgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return Bauteil-Objekt, das dem übergebenen Schlüssel entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 */

	public ComponentPart findById(int id) {

		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt
					.executeQuery("SELECT id, name, description, material, dateOfModification, cID FROM ComponentPart "
							+ "WHERE id=" + id + " ORDER BY name");

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
			 * werden. Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				ComponentPart p = new ComponentPart();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDescription(rs.getString("description"));
				p.setMaterial(rs.getString("material"));
				p.setDateOfModification(rs.getDate("dateOfModification"));
				p.setCID(rs.getInt("cID"));
				
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Auslesen aller Bauteile.
	 * 
	 * @return Ein Vektor mit ComponentPart-Objekten, die sämtliche Kunden
	 *         repräsentieren. Bei evtl. Exceptions wird ein partiell gef�llter
	 *         oder ggf. auch leerer Vetor zurückgeliefert.
	 */

	public Vector<ComponentPart> findByAll() {

		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<ComponentPart> result = new Vector<ComponentPart>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, description, material, dateOfModification, cID "
							+ "FROM ComponentPart " + "ORDER BY name");

			// Für jeden Eintrag im Suchergebnis wird nun ein ComponentPart-Objekt
			// erstellt.
			while (rs.next()) {
				ComponentPart p = new ComponentPart();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDescription(rs.getString("description"));
				p.setMaterial(rs.getString("material"));
				p.setDateOfModification(rs.getDate("dateOfModification"));
				p.setCID(rs.getInt("cID"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	public ComponentPart findByName(String name){
		
	}

	public ComponentPart findByMaterial(String material){
		
	}
	
	public BillOfMaterial getBom(ComponentPart p){
		
	}
	
	public Component getComponentOf(ComponentPart p){
		
	}
	
}

