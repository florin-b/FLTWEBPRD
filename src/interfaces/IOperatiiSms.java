package interfaces;

import java.sql.SQLException;
import java.util.List;

import beans.BeanSmsEmis;

public interface IOperatiiSms {
	List<BeanSmsEmis>  getSmsEmis(String dataSms, String filiala) throws SQLException;
}
