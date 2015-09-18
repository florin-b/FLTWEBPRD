package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import beans.PozitieClient;
import beans.PozitieGps;
import beans.RezultatTraseu;
import beans.TraseuBorderou;
import utils.Constants;
import utils.MapUtils;

public class CalculeazaTraseu {

	private List<PozitieClient> pozitiiClienti;
	private List<TraseuBorderou> traseuBorderou;
	private List<RezultatTraseu> rezultatTraseu;
	private List<RezultatTraseu> stareTraseu;

	private enum EvenimentClient {
		SOSIRE, PLECARE
	};

	public CalculeazaTraseu() {
		this.rezultatTraseu = new ArrayList<RezultatTraseu>();
	}

	public List<PozitieClient> getPozitiiClienti() {
		return pozitiiClienti;
	}

	public void setPozitiiClienti(List<PozitieClient> pozitiiClienti) {
		this.pozitiiClienti = pozitiiClienti;
	}

	public List<TraseuBorderou> getTraseuBorderou() {
		return traseuBorderou;
	}

	public void setTraseuBorderou(List<TraseuBorderou> traseuBorderou) {
		this.traseuBorderou = traseuBorderou;
	}

	public void setRezultatTraseu(List<RezultatTraseu> rezultatTraseu) {
		this.rezultatTraseu = rezultatTraseu;
	}

	private void descoperaEvenimente() {

		double distanta = 0;
		for (TraseuBorderou traseu : traseuBorderou) {
			for (PozitieClient pozitieClient : pozitiiClienti) {

				distanta = MapUtils.distanceXtoY(traseu.getLatitudine(), traseu.getLongitudine(), pozitieClient.getLatitudine(), pozitieClient.getLongitudine(),
						"K");

				pozitieClient.setDistantaCamion((int) distanta);

				if (conditiiSosire(traseu, distanta)) {
					adaugaEveniment(pozitieClient, traseu, EvenimentClient.SOSIRE);

				}

				if (conditiiPlecare(traseu, pozitieClient)) {
					adaugaEveniment(pozitieClient, traseu, EvenimentClient.PLECARE);

				}

			}

		}

	}

	private boolean conditiiSosire(TraseuBorderou traseu, double distanta) {
		if (traseu.getViteza() == 0 && distanta <= Constants.RAZA_CLIENT_KM)
			return true;

		return false;
	}

	private boolean conditiiPlecare(TraseuBorderou traseu, PozitieClient pozitieClient) {
		if (traseu.getViteza() >= Constants.VITEZA_MINIMA_PLECARE && getCoordSosire(pozitieClient.getCodClient()) != null)
			return true;

		return false;
	}

	private void adaugaEveniment(PozitieClient pozitieClient, TraseuBorderou traseuBord, EvenimentClient tipEveniment) {

		boolean found = false;

		if (rezultatTraseu.size() == 0) {
			RezultatTraseu evenim = new RezultatTraseu();

			evenim.setCodClient(pozitieClient.getCodClient());
			evenim.setNumeClient(pozitieClient.getNumeClient());
			evenim.setSosire(new PozitieGps(traseuBord.getDataInreg(), traseuBord.getLatitudine(), traseuBord.getLongitudine()));

			rezultatTraseu.add(evenim);
			found = true;
		}

		else {
			for (RezultatTraseu rez : rezultatTraseu) {
				if (rez.getCodClient().equals(pozitieClient.getCodClient())) {

					if (tipEveniment == EvenimentClient.SOSIRE) {
						if (rez.getSosire() != null)
							found = true;
					}

					if (tipEveniment == EvenimentClient.PLECARE) {
						if (rez.getPlecare() != null)
							found = true;
					}

				}
			}

		}

		if (!found) {

			if (tipEveniment == EvenimentClient.SOSIRE) {
				RezultatTraseu evenim = new RezultatTraseu();
				evenim.setCodClient(pozitieClient.getCodClient());
				evenim.setNumeClient(pozitieClient.getNumeClient());
				evenim.setSosire(new PozitieGps(traseuBord.getDataInreg(), traseuBord.getLatitudine(), traseuBord.getLongitudine()));
				rezultatTraseu.add(evenim);
			}

			if (tipEveniment == EvenimentClient.PLECARE) {
				for (RezultatTraseu traseu : rezultatTraseu) {
					if (traseu.getCodClient().equals(pozitieClient.getCodClient())) {
						traseu.setPlecare(new PozitieGps(traseuBord.getDataInreg(), traseuBord.getLatitudine(), traseuBord.getLongitudine()));
						break;
					}
				}

			}

		}

	}

	public List<RezultatTraseu> getRezultatTraseu() {
		return rezultatTraseu;
	}

	public Set<RezultatTraseu> getStareTraseu() {

		descoperaEvenimente();

		stareTraseu = new ArrayList<RezultatTraseu>(rezultatTraseu);

		RezultatTraseu tempRez = null;

		for (PozitieClient client : pozitiiClienti) {
			tempRez = new RezultatTraseu();
			tempRez.setCodClient(client.getCodClient());
			tempRez.setNumeClient(client.getNumeClient());
			tempRez.setSosire(null);
			tempRez.setPlecare(null);
			tempRez.setDistantaCamion(client.getDistantaCamion());

			for (RezultatTraseu rezultat : rezultatTraseu) {
				if (rezultat.getCodClient().equals(client.getCodClient())) {
					tempRez.setSosire(rezultat.getSosire());
					tempRez.setPlecare(rezultat.getPlecare());

				}
			}

			stareTraseu.add(tempRez);
		}

		Set<RezultatTraseu> setStare = new HashSet<RezultatTraseu>(stareTraseu);

		return setStare;

	}

	public PozitieGps getCoordSosire(String codClient) {

		for (RezultatTraseu tras : rezultatTraseu) {
			if (tras.getCodClient().equals(codClient))
				return tras.getSosire();
		}

		return null;

	}

}
