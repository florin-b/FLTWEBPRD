package interfaces;

import java.sql.SQLException;
import java.util.List;

import beans.Masina;
import beans.PozitieMasina;

public interface ILocalizare {
	public String getPozitieMasini(String listMasini) throws SQLException;
}
