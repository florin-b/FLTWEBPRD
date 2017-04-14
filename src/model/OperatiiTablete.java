package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanStareTableta;

import database.DBManager;
import queries.SqlQueries;
import utils.DateUtils;

public class OperatiiTablete {

	public List<BeanStareTableta> getTableteSoferi(String codSofer) throws SQLException {

		List<BeanStareTableta> listTablete = new ArrayList<>();

		try (Connection conn = DBManager.getProdInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getTableteSofer(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

			stmt.setString(1, codSofer);
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				BeanStareTableta tableta = new BeanStareTableta();

				tableta.setId(rs.getString("codtableta"));
				tableta.setDataInreg(rs.getString("datainreg"));
				tableta.setStare(rs.getString("stare"));

				listTablete.add(tableta);
			}

		}

		return listTablete;
	}

	public void gestioneazaCod(String codTableta, String codSofer, String operatie) throws SQLException {

		if (operatie.equalsIgnoreCase("aloca"))
			adaugaCod(codTableta, codSofer);
		if (operatie.equalsIgnoreCase("sterge"))
			invalideazaTablete(codSofer);

	}

	private String adaugaCod(String codTableta, String codSofer) throws SQLException {

		try (Connection conn = DBManager.getProdInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.invalidateAllCodes(), ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {

			stmt.setString(1, codSofer);
			stmt.executeQuery();

			PreparedStatement innerStmt = conn.prepareCall(SqlQueries.addCodTableta());

			innerStmt.setString(1, codSofer);
			innerStmt.setString(2, codTableta);
			innerStmt.setString(3, DateUtils.getCurrentDate());
			innerStmt.setString(4, "1");
			innerStmt.setString(5, "1");
			innerStmt.setString(6, DateUtils.getCurrentTime());

			innerStmt.execute();

			innerStmt.close();

		}

		return null;
	}

	private String invalideazaTablete(String codSofer) throws SQLException {

		try (Connection conn = DBManager.getProdInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.invalidateAllCodes(), ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {

			stmt.setString(1, codSofer);
			stmt.executeQuery();

		}

		return null;
	}

}
