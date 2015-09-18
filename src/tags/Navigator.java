package tags;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import model.NavigationDetails;

public class Navigator extends SimpleTagSupport {

	private ArrayList<NavigationDetails> navigationLinks;

	private void CreateNavigationLinks() {

		navigationLinks = new ArrayList<NavigationDetails>();
		String root = "http://localhost:8080/";
		root = "http://10.1.0.133:8080/";
		String app = "FlotaWeb/";

		NavigationDetails nd = new NavigationDetails();

		nd.setLink(String.format("%s%smain.jsp", root, app));
		nd.setText("Acasa");
		navigationLinks.add(nd);

		nd = new NavigationDetails();
		nd.setLink(String.format("%s%sauth/activitateSoferiOptiuni.jsp", root, app));
		nd.setText("Activitate soferi");
		navigationLinks.add(nd);

		nd = new NavigationDetails();
		nd.setLink(String.format("%s%sauth/pozitieMasiniOptiuni.jsp", root, app));
		nd.setText("Pozitie masini");
		navigationLinks.add(nd);

		nd = new NavigationDetails();
		nd.setLink(String.format("%s%sexit.jsp", root, app));
		nd.setText("Logout");
		navigationLinks.add(nd);

	}

	public void doTag() throws JspException, IOException {

		CreateNavigationLinks();

		for (NavigationDetails nd : navigationLinks) {
			getJspContext().setAttribute("navdetails", nd);
			getJspBody().invoke(null);
		}
	}

}
