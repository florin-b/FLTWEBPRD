package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanSmsEmis;
import database.DBManager;
import interfaces.IOperatiiSms;
import utils.Formatting;

public class OperatiiSms implements IOperatiiSms {

	@Override
	public List<BeanSmsEmis> getSmsEmis(String dataSms, String filiala) throws SQLException {

		List<BeanSmsEmis> listSms = new ArrayList<>();

		String sqlString = " select a.codsofer, a.document, a.client, a.data, a.ora, b.dataexp, b.oraexp from sapprd.zevenimentsofer a, sapprd.zsmsclienti b, soferi c " +
				" where a.data =? and b.borderou = a.document and b.codadresa = a.codadresa and a.codsofer = c.cod and c.fili = 'GL10' " + 
				"  order by a.codsofer, a.document, a.data, a.ora ";

		try (Connection conn = DBManager.getProdInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sqlString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

			stmt.setString(1, dataSms);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				BeanSmsEmis smsEmis = new BeanSmsEmis();

				smsEmis.setCodSofer(rs.getString("codsofer"));
				smsEmis.setDocument(rs.getString("document"));
				smsEmis.setClient(rs.getString("client"));
				smsEmis.setDataSosireClient(Formatting.formatFromSap(rs.getString("data") + " " + rs.getString("ora")));
				smsEmis.setDataEmitereSms(Formatting.formatFromSap(rs.getString("dataexp") + " " + rs.getString("oraexp")));

				listSms.add(smsEmis);

			}

		}

		return listSms;

	}

}
