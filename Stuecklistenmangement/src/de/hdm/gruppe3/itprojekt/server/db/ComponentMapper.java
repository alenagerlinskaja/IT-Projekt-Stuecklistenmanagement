package de.hdm.gruppe3.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.gruppe3.itprojekt.shared.bo.BillOfMaterial;
import de.hdm.gruppe3.itprojekt.shared.bo.Component;
import de.hdm.gruppe3.itprojekt.shared.bo.ComponentPart;
import de.hdm.gruppe3.itprojekt.shared.bo.FinishedProduct;

public class ComponentMapper {

	private static ComponentMapper componentMapper = null;

	protected ComponentMapper() {
	}

	public static ComponentMapper componentMapper() {
		if (componentMapper == null) {
			componentMapper = new ComponentMapper();
		}

		return componentMapper;
	}

	public Component insert(Component c) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM Component ");

			if (rs.next()) {

				c.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

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

		return c;

	}

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

		return c;

	}

	public Component deleteComponentOf(BillOfMaterial b) {

	}

	public Component findById(int id) {

	}

	public Vector<Component> findByAll() {

	}

	public Vector<Component> findByBom(int id) {

	}

	public Vector<Component> findByBom(BillOfMaterial bom) {

	}

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
