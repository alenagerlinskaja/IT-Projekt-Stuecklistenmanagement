package de.hdm.gruppe3.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.gruppe3.itprojekt.shared.bo.*;

/**
 * Mapper-Klasse, die <code>BillOfMaterial</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Thies
 */

public class BillOfMaterialMapper {

	/**
	 * Die Klasse BillOfMaterialMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * f�r s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 */

	private static BillOfMaterialMapper billOfMaterialMapper = null;

	/**
	 * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 * 
	 */

	protected BillOfMaterialMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>BillOfMaterialMapper.billOfMaterialMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine
	 * einzige Instanz von <code>BillOfMaterialMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> BillOfMaterialMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>BillOfMaterialMapper</code>-Objekt.
	 */

	public static BillOfMaterialMapper billOfMaterialMapper() {
		if (billOfMaterialMapper == null) {
			billOfMaterialMapper = new BillOfMaterialMapper();
		}

		return billOfMaterialMapper;
	}

	/**
	 * Einf�gen eines <code>BillOfMaterial</code>-Objekts in die Datenbank.
	 * Dabei wird auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und
	 * ggf. berichtigt.
	 * 
	 * @param b
	 *            das zu speichernde Objekt
	 * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 * @author Thies
	 */

	public BillOfMaterial insert(BillOfMaterial b) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zun�chst schauen wir nach, welches der momentan h�chste
			 * Prim�rschl�sselwert ist.
			 * 
			 * @author Thies
			 */

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM BillOfMaterial ");

			// Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein

			if (rs.next()) {

				/*
				 * b erh�lt den bisher maximalen, nun um 1 inkrementierten
				 * Prim�rschl�ssel.
				 */

				b.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();

				// Jetzt erst erfolgt die tats�chliche Einf�geoperation

				stmt.executeUpdate("INSERT INTO BillOfMaterial (id, name, creationDate) "
						+ "VALUES ("
						+ b.getId()
						+ ","
						+ b.getName()
						+ ","
						+ b.getCreationDate() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		/*
		 * R�ckgabe, des evtl. korrigierten BillOfMaterial.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte �bergeben werden, w�re die Anpassung des
		 * BillOfMaterialt-Objekts auch ohne diese explizite R�ckgabe au�erhalb
		 * dieser Methode sichtbar. Die explizite R�ckgabe von b ist eher ein
		 * Stilmittel, um zu signalisieren, dass sich das Objekt evtl. im Laufe
		 * der Methode ver�ndert hat.
		 */

		return b;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param b
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter �bergebene Objekt
	 * @author Thies
	 */

	public BillOfMaterial update(BillOfMaterial b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE BillOfMaterial " + "SET name=\""
					+ b.getName() + "\", " + "creationDate=\""
					+ b.getCreationDate() + "\" " + "WHERE id=" + b.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Um Analogie zu insert(BillOfMaterial b) zu wahren, geben wir b
		// zur�ck

		return b;
	}

	/**
	 * L�schen der Daten eines <code>BillOfMaterial</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param b
	 *            das aus der DB zu l�schende "Objekt"
	 * @author Thies
	 */

	public void delete(BillOfMaterial b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM BillOfMaterial " + "WHERE id="
					+ b.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Suchen einer St�ckliste mit vorgegebener Id. Da diese eindeutig ist, wird
	 * genau ein Objekt zur�ckgegeben.
	 * 
	 * @param id
	 *            Prim�rschl�sselattribut (->DB)
	 * @return St�cklisten-Objekt, das dem �bergebenen Schl�ssel entspricht,
	 *         null bei nicht vorhandenem DB-Tupel.
	 */

	public BillOfMaterial findById(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausf�llen und als Query an die DB schicken
			ResultSet rs = stmt
					.executeQuery("SELECT id, name, creationDate FROM BillOfMaterial "
							+ "WHERE id=" + id + " ORDER BY name");

			/*
			 * Da id Prim�rschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
			 * werden. Pr�fe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				BillOfMaterial b = new BillOfMaterial();
				b.setId(rs.getInt("id"));
				b.setName(rs.getString("name"));
				b.setCreationDate(rs.getDate("creationDate"));
				return b;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Auslesen aller St�cklisten.
	 * 
	 * @return Ein Vektor mit BillOfMaterial-Objekten, die s�mtliche St�cklisten
	 *         repr�sentieren. Bei evtl. Exceptions wird ein partiell gef�llter
	 *         oder ggf. auch leerer Vetor zur�ckgeliefert.
	 */
	public Vector<BillOfMaterial> findAll() {
		Connection con = DBConnection.connection();
		// Ergebnisvektor vorbereiten
		Vector<BillOfMaterial> result = new Vector<BillOfMaterial>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, name, creationDate "
					+ "FROM BillOfMaterial" + "ORDER BY name");

			// F�r jeden Eintrag im Suchergebnis wird nun ein
			// BillOfMaterial-Objekt
			// erstellt.
			while (rs.next()) {
				BillOfMaterial b = new BillOfMaterial();
				b.setId(rs.getInt("id"));
				b.setName(rs.getString("name"));
				b.setCreationDate(rs.getDate("creationDate"));

				// Hinzuf�gen des neuen Objekts zum Ergebnisvektor
				result.addElement(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zur�ckgeben
		return result;
	}

}
