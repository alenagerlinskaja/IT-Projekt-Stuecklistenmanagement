package de.hdm.gruppe3.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.gruppe3.itprojekt.shared.bo.*;

/**
 * Mapper-Klasse, die <code>FinishedProduct</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Thies
 */

public class FinishedProductMapper {

	/**
	 * Die Klasse FinishedProductMapper wird nur einmal instantiiert. Man
	 * spricht hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * f�r s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 */

	private static FinishedProductMapper finishedProductMapper = null;

	/**
	 * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 * 
	 */

	protected FinishedProductMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>FinishedProductMapper.finishedProductMapper()</code>. Sie stellt
	 * die Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine
	 * einzige Instanz von <code>FinishedProductMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> FinishedProductMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>FinishedProductMapper</code>-Objekt.
	 */

	public static FinishedProductMapper finishedProductMapper() {
		if (finishedProductMapper == null) {
			finishedProductMapper = new FinishedProductMapper();
		}

		return finishedProductMapper;
	}

	/**
	 * Einf�gen eines <code>FinishedProduct</code>-Objekts in die Datenbank.
	 * Dabei wird auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und
	 * ggf. berichtigt.
	 * 
	 * @param f
	 *            das zu speichernde Objekt
	 * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 * @author Thies
	 */

	public FinishedProduct insert(FinishedProduct f) {

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
					+ "FROM FinishedProduct ");

			// Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein

			if (rs.next()) {

				/*
				 * f erh�lt den bisher maximalen, nun um 1 inkrementierten
				 * Prim�rschl�ssel.
				 */

				f.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();

				// Jetzt erst erfolgt die tats�chliche Einf�geoperation

				stmt.executeUpdate("INSERT INTO FinishedProduct (id, name, dateOfModification, CID) "
						+ "VALUES ("
						+ f.getId()
						+ ","
						+ f.getName()
						+ ","
						+ f.getDateOfModification() + "," + f.getcID() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		/*
		 * R�ckgabe, des evtl. korrigierten FinishedProducts.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte �bergeben werden, w�re die Anpassung des
		 * FinishedProduct-Objekts auch ohne diese explizite R�ckgabe au�erhalb
		 * dieser Methode sichtbar. Die explizite R�ckgabe von f ist eher ein
		 * Stilmittel, um zu signalisieren, dass sich das Objekt evtl. im Laufe
		 * der Methode ver�ndert hat.
		 */

		return f;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param f
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter �bergebene Objekt
	 * @author Thies
	 */

	public FinishedProduct update(FinishedProduct f) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE FinishedProduct " + "SET name=\""
					+ f.getName() + "\", " + "dateOfModification=\""
					+ f.getDateOfModification() + "\", " + "CID=\""
					+ f.getcID() + "\" " + "WHERE id=" + f.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Um Analogie zu insert(Finishedproduct f) zu wahren, geben wir f
		// zur�ck

		return f;
	}

	/**
	 * L�schen der Daten eines <code>FinishedProduct</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param f
	 *            das aus der DB zu l�schende "Objekt"
	 * @author Thies
	 */

	public void delete(FinishedProduct f) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM FinishedProduct " + "WHERE id="
					+ f.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Suchen eines Enderzeuhnisses mit vorgegebener Id. Da diese eindeutig ist,
	 * wird genau ein Objekt zur�ckgegeben.
	 * 
	 * @param id
	 *            Prim�rschl�sselattribut (->DB)
	 * @return Enderzeugnis-Objekt, das dem �bergebenen Schl�ssel entspricht,
	 *         null bei nicht vorhandenem DB-Tupel.
	 */

	public FinishedProduct findById(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausf�llen und als Query an die DB schicken
			ResultSet rs = stmt
					.executeQuery("SELECT id, name, dateOfModification, CID FROM FinishedProduct "
							+ "WHERE id=" + id + " ORDER BY name");

			/*
			 * Da id Prim�rschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
			 * werden. Pr�fe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				FinishedProduct f = new FinishedProduct();
				f.setId(rs.getInt("id"));
				f.setName(rs.getString("name"));
				f.setDateOfModification(rs.getDate("dateOfModification"));
				f.setcID(rs.getInt("CID"));
				return f;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Auslesen aller Enderzeugnisse.
	 * 
	 * @return Ein Vektor mit FinishedProduct-Objekten, die s�mtliche Kunden
	 *         repr�sentieren. Bei evtl. Exceptions wird ein partiell gef�llter
	 *         oder ggf. auch leerer Vetor zur�ckgeliefert.
	 */
	public Vector<FinishedProduct> findAll() {
		Connection con = DBConnection.connection();
		// Ergebnisvektor vorbereiten
		Vector<FinishedProduct> result = new Vector<FinishedProduct>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, dateOfModification, CID "
							+ "FROM FinishedProduct" + "ORDER BY name");

			// F�r jeden Eintrag im Suchergebnis wird nun ein Customer-Objekt
			// erstellt.
			while (rs.next()) {
				FinishedProduct f = new FinishedProduct();
				f.setId(rs.getInt("id"));
				f.setName(rs.getString("name"));
				f.setDateOfModification(rs.getDate("dateOfModification"));
				f.setcID(rs.getInt("CID"));

				// Hinzuf�gen des neuen Objekts zum Ergebnisvektor
				result.addElement(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zur�ckgeben
		return result;
	}

}