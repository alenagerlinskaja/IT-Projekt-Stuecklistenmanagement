package de.hdm.gruppe3.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.gruppe3.itprojekt.shared.bo.*;

public class FinishedProductMapper {

	private static FinishedProductMapper finishedProductMapper = null;

	protected FinishedProductMapper() {
	}

	public static FinishedProductMapper finishedProductMapper() {
		if (finishedProductMapper == null) {
			finishedProductMapper = new FinishedProductMapper();
		}

		return finishedProductMapper;
	}

	public FinishedProduct insert(FinishedProduct f) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM FinishedProduct ");

			if (rs.next()) {

				f.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO FinishedProduct (id, name, dateOfModification, CID) "
						+ "VALUES ("
						+ f.getId()
						+ ","
						+ f.getName()
						+ ","
						+ f.getDateOfModification() + "," + f.getCID() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return f;
	}

}
