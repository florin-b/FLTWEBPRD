package tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import beans.Filiala;

import database.OperatiiFiliala;

public class GetFilialeTag extends SimpleTagSupport {

	private String name, id;

	private static final String ATTR_TEMPLATE = "%s='%s' ";

	private String fili;
	private String tipAcces;

	public String getFili() {
		return fili;
	}

	public void setFili(String fili) {
		this.fili = fili;
	}

	public String getTipAcces() {
		return tipAcces;
	}

	public void setTipAcces(String tipAcces) {
		this.tipAcces = tipAcces;
	}

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
		String filialaUser = fili;

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

		if (fili.equals("GL_CENTRAL")) {
			if (tipAcces.equals("20") || tipAcces.equals("13") || tipAcces.equals("140"))
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

		return listFiliale;
	}

}
