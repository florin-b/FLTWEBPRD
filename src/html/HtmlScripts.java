package html;

import java.util.List;

import beans.PozitieMasina;

public class HtmlScripts {

	public static String multipleMarkersMap(List<PozitieMasina> listPozitii) {

		String centerScreenPoint = listPozitii.get(listPozitii.size() / 2).getLatitudine() + " , "
				+ listPozitii.get(listPozitii.size() / 2).getLongitudine();

		StringBuilder htmlMapPoint = new StringBuilder();
		htmlMapPoint.append("<!DOCTYPE html>  <html>  <head>  <title>Simple Map</title> ");
		htmlMapPoint.append(" <meta name='viewport' content='initial-scale=1.0, user-scalable=no'>  <meta charset='utf-8'> ");
		htmlMapPoint.append(" <link rel=\"stylesheet\" type=\"text/css\"");
		htmlMapPoint.append(" href=\"OneMarkerStyle.css\"> ");
		htmlMapPoint.append(" <link rel=\"stylesheet\" type=\"text/css\"");
		htmlMapPoint.append(" href=\"MarkerLabelStyle.css\"> ");
		htmlMapPoint.append(" <script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyBhGZckc6WAio9WiiLstQTTpVtAvQ7kIEc'></script> ");
		htmlMapPoint.append("<script type=\"text/javascript\" src=\'");
		htmlMapPoint.append("markerwithlabel.js" + "' > ");

		htmlMapPoint.append("</script>");
		htmlMapPoint.append("<script type=\"text/javascript\">");
		htmlMapPoint.append(" var locations = [" + loadLocations(listPozitii) + " ]; ");
		htmlMapPoint.append(" var map;  function initialize() { ");
		htmlMapPoint.append(" var mapOptions = {  ");
		htmlMapPoint.append(" zoom : 7,  ");
		htmlMapPoint.append(" center: new google.maps.LatLng(" + centerScreenPoint + " ),  ");
		htmlMapPoint.append(" mapTypeId : google.maps.MapTypeId.ROADMAP }; ");
		htmlMapPoint.append(" map = new google.maps.Map(document.getElementById('map-canvas'),       mapOptions); ");
		htmlMapPoint.append(" var infowindow = new google.maps.InfoWindow(); ");
		htmlMapPoint.append(" var marker; ");
		htmlMapPoint.append(" for (i = 0; i < locations.length; i++) {   ");
		htmlMapPoint.append("  marker = new MarkerWithLabel({ ");
		htmlMapPoint.append("  position: new google.maps.LatLng(locations[i][0], locations[i][1]) , ");
		htmlMapPoint.append("  map: map, ");
		htmlMapPoint.append("  labelContent: locations[i][2], ");
		htmlMapPoint.append("  labelAnchor: new google.maps.Point(22, 0), ");
		htmlMapPoint.append(" labelClass: \"labels\", ");
		htmlMapPoint.append(" labelStyle: {opacity: 0.75} ");
		htmlMapPoint.append("   });  ");
		htmlMapPoint.append("  google.maps.event.addListener(marker, \"click\", (function(marker, i) { ");
		htmlMapPoint.append("    return function() { ");
		htmlMapPoint.append("    infowindow.setContent(locations[i][4] + ' <br>' + locations[i][3] + ' km/h'); ");
		htmlMapPoint.append("    infowindow.open(map, marker); ");
		htmlMapPoint.append("    } ");
		htmlMapPoint.append("  })(marker, i )); ");
		htmlMapPoint.append(" } ");
		htmlMapPoint.append(" } ");
		htmlMapPoint.append(" google.maps.event.addDomListener(window, 'load', initialize); </script> </head> ");
		htmlMapPoint.append(" <body> <div id='map-canvas'></div> </body> </html> ");

		return htmlMapPoint.toString();
	}

	private static String loadLocations(List<PozitieMasina> listPozitii) {

		String stringGeo = "";

		for (int i = 0; i < listPozitii.size(); i++) {
			if (stringGeo.length() == 0) {
				stringGeo = "[" + listPozitii.get(i).getLatitudine() + "," + listPozitii.get(i).getLongitudine() + ",'"
						+ listPozitii.get(i).getNrAuto() + "','" + listPozitii.get(i).getViteza() + "','" + listPozitii.get(i).getData() + "' ]";
			} else {
				stringGeo += ",[" + listPozitii.get(i).getLatitudine() + "," + listPozitii.get(i).getLongitudine() + ",'"
						+ listPozitii.get(i).getNrAuto() + "','" + listPozitii.get(i).getViteza() + "','" + listPozitii.get(i).getData() + "' ]";
			}
		}

		return stringGeo;
	}

}
