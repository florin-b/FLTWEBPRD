package tags;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import beans.UserInfo;
import model.NavigationDetails;

public class Navigator extends SimpleTagSupport {

	private ArrayList<NavigationDetails> navigationLinks;

	private void CreateNavigationLinks() {

		navigationLinks = new ArrayList<NavigationDetails>();

		PageContext pageContext = (PageContext) getJspContext();
		String root = pageContext.getServletContext().getContextPath();

		NavigationDetails nd = new NavigationDetails();

		nd.setLink(String.format("%s/main.jsp", root));
		nd.setText("Acasa");
		navigationLinks.add(nd);

		if (!UserInfo.getInstance().getTipAcces().equals("47") && !UserInfo.getInstance().getTipAcces().equals("89")) {
			nd = new NavigationDetails();
			nd.setLink(String.format("%s/auth/activitateSoferiOptiuni.jsp", root));
			nd.setText("Activitate soferi");
			navigationLinks.add(nd);

			nd = new NavigationDetails();
			nd.setLink(String.format("%s/auth/pozitieMasiniOptiuni.jsp", root));
			nd.setText("Pozitie masini");
			navigationLinks.add(nd);

			nd = new NavigationDetails();
			nd.setLink(String.format("%s/auth/traseuSoferi.jsp", root));
			nd.setText("Traseu masina");
			navigationLinks.add(nd);

			nd = new NavigationDetails();
			nd.setLink(String.format("%s/auth/gpsInactiv.jsp", root));
			nd.setText("Module GPS inactive");
			navigationLinks.add(nd);
		}

		if (UserInfo.getInstance().getTipAcces().equals("8")) {
			nd = new NavigationDetails();
			nd.setLink(String.format("%s/auth/analizaSmsClienti.jsp", root));
			nd.setText("SMS Clienti");
			navigationLinks.add(nd);

		}

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/auth/gestiuneTablete.jsp", root));
		nd.setText("Gestiune tablete");
		navigationLinks.add(nd);

		if (!UserInfo.getInstance().getTipAcces().equals("47") && !UserInfo.getInstance().getTipAcces().equals("89")) {
			nd = new NavigationDetails();
			nd.setLink(String.format("%s/auth/avarieMasina.jsp", root));
			nd.setText("Avarie masina");
			navigationLinks.add(nd);
		}

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/exit.jsp", root));
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
