package tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import beans.Filiala;
import beans.UserInfo;
import database.OperatiiFiliala;

public class GetFilialeTag extends SimpleTagSupport {

	private String name, id;

	private static final String ATTR_TEMPLATE = "%s='%s' ";

	public void doTag() throws JspException, IOException {

		PageContext pageContext = (PageContext) getJspContext();

		JspWriter out = pageContext.getOut();
		out.print("<select size=10 ");
		out.print(String.format(ATTR_TEMPLATE, "name", this.name));
		out.print(String.format(ATTR_TEMPLATE, "id", this.id));
		out.println(">");

		List<Filiala> listFiliale = getFilialeAcces();

		for (Filiala filiala : listFiliale) {

			out.print("  <option value='");
			out.print(filiala.getCod());
			out.print("'>");
			out.print(filiala.getNume());
			out.println("</option>");

		}

		out.println("</select>");

	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String getNumeFilialaUser() {
		String filialaUser = UserInfo.getInstance().getFiliala();

		if (filialaUser.equals("ORADEA"))
			filialaUser = "BIHOR";
		else if (filialaUser.equals("CRAIOVA"))
			filialaUser = "DOLJ";
		else if (filialaUser.equals("DEVA"))
			filialaUser = "HUNEDOARA";
		else if (filialaUser.equals("BAIA"))
			filialaUser = "MARAMURES";
		else if (filialaUser.equals("PIATRA"))
			filialaUser = "NEAMT";
		else if (filialaUser.equals("PLOIESTI"))
			filialaUser = "PRAHOVA";
		else if (filialaUser.equals("FOCSANI"))
			filialaUser = "VRANCEA";
		else if (filialaUser.equals("PITESTI"))
			filialaUser = "ARGES";

		return filialaUser;
	}

	private List<Filiala> getFilialeAcces() {

		List<Filiala> listFiliale;

		if (UserInfo.getInstance().getFiliala().equals("GL_CENTRAL")) {
			if (UserInfo.getInstance().getTipAcces().equals("20") || UserInfo.getInstance().getTipAcces().equals("13"))
				listFiliale = OperatiiFiliala.getListFilialeStatic();
			else {
				Filiala filiala = new Filiala();
				filiala.setCod("GL90");
				filiala.setNume("GALATI CENTRAL");
				listFiliale = new ArrayList<>();
				listFiliale.add(filiala);
			}
		} else
			listFiliale = OperatiiFiliala.getListFiliale(getNumeFilialaUser());

		if ((UserInfo.getInstance().getTipAcces().equals("20") || UserInfo.getInstance().getTipAcces().equals("13"))
				&& UserInfo.getInstance().getFiliala().equals("GL_CENTRAL"))
			listFiliale = OperatiiFiliala.getListFilialeStatic();

		return listFiliale;
	}

}
