package tags;

import java.io.IOException;
import java.sql.SQLException;
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

	public void doTag() throws JspException, IOException {

		PageContext pageContext = (PageContext) getJspContext();

		JspWriter out = pageContext.getOut();
		out.print("<select size=10 ");
		out.print(String.format(ATTR_TEMPLATE, "name", this.name));
		out.print(String.format(ATTR_TEMPLATE, "id", this.id));
		out.println(">");

		OperatiiFiliala operatiiFiliala = new OperatiiFiliala();

		List<Filiala> listFiliale = operatiiFiliala.getListFilialeStatic();

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

}
