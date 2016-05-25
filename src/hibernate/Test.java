package hibernate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.Address;
import beans.Sofer;
import database.OperatiiTraseu;
import utils.MapUtils;
import utils.Utils;

public class Test {

	public static void main(String[] args) throws Exception {
		System.out.println("Hello world!");
		
		
		
		OperatiiTraseu opTraseu = new OperatiiTraseu();
		
		System.out.println(opTraseu.getCoordClientiBorderou("0001349926"));
		

	}

	private static void getCoord() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		Date start = Calendar.getInstance().getTime();

		Address address = new Address();
		/*
		 * address.setStreet("soseaua Bucureºti - Ploieºti");
		 * address.setNumber("9-13"); address.setSector("sector 1");
		 * address.setCity("bucuresti");
		 */

		address.setStreet("gheorghe caranfil");
		address.setNumber("");
		address.setSector("NEAMT");
		address.setCity("SAVINESTI");

		System.out.println(MapUtils.geocodeAddress(address));

		Date stop = Calendar.getInstance().getTime();

		double diff = stop.getTime() - start.getTime();

		System.out.println("Diff =" + diff);
	}

	private static void getList() {
		Session session = HibernateUtilities.getSessionFactoryInstance();

		session.beginTransaction();

		Query query = session.createQuery("from Sofer where numeSofer =:nume");

		query.setParameter("nume", "HORVAT MIHAI");

		List<Sofer> soferi = query.list();

		System.out.println(soferi);

		session.getTransaction().commit();

		session.close();

	}

	private static void getObject() {
		Session session = HibernateUtilities.getSessionFactoryInstance();

		session.beginTransaction();

		Sofer sofer = (Sofer) session.get(Sofer.class, "00140603");

		System.out.println("Sofer = " + sofer.getNumeSofer());

		session.getTransaction().commit();

		session.close();

		HibernateUtilities.getSessionFactoryInstance().close();
	}

}
