function getPozitieMasini(strLocations) {

	var locations = strLocations.split('#');

	var mapCenterPosition = parseInt(locations.length / 2);

	var centerArrayCoord = locations[mapCenterPosition].split(',');
	var mapCenterPosition = new google.maps.LatLng(centerArrayCoord[0], centerArrayCoord[1]);

	var map;
	var mapOptions = {
		zoom : 8,
		center : mapCenterPosition,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById('pozitieMasini'), mapOptions);
	var infowindow = new google.maps.InfoWindow();
	var marker;
	var loc;
	var carInfo;

	for (i = 0; i < locations.length; i++) {

		loc = locations[i].split(',');
		
		
		

		// borderouri active
		if (loc[5] == 0) {
			marker = new MarkerWithLabel({
				position : new google.maps.LatLng(loc[0], loc[1]),
				map : map,
				icon : '../images/red_truck.png',
				labelContent : loc[2],
				labelAnchor : new google.maps.Point(22, 0),
				labelClass : "labels",
				labelStyle : {
					opacity : 0.75
				}
			});

			google.maps.event.addListener(marker, "mouseover", (function(marker, i) {
				return function() {
					infowindow.setContent(locations[i].split(',')[6] + '<br>' + locations[i].split(',')[4] + ' <br>' + locations[i].split(',')[3] + ' km/h'
							+ ' <br>' + locations[i].split(',')[7]);
					infowindow.open(map, marker);
				}
			})(marker, i));
		}
	}

	for (i = 0; i < locations.length; i++) {

		loc = locations[i].split(',');

		// borderouri terminate
		if (loc[5] == 1) {
			marker = new MarkerWithLabel({
				position : new google.maps.LatLng(loc[0], loc[1]),
				map : map,
				icon : '../images/green_truck.png',
				labelContent : loc[2],
				labelAnchor : new google.maps.Point(22, 0),
				labelClass : "labels",
				labelStyle : {
					opacity : 0.75
				}
			});

			google.maps.event.addListener(marker, "mouseover", (function(marker, i) {
				return function() {
					infowindow.setContent(locations[i].split(',')[6] + '<br>' + locations[i].split(',')[4] + ' <br>' + locations[i].split(',')[3] + ' km/h'
							+ ' <br>' + locations[i].split(',')[7]);
					infowindow.open(map, marker);
				}
			})(marker, i));
		}
	}

	for (i = 0; i < locations.length; i++) {

		loc = locations[i].split(',');

		// fara borderou
		if (loc[5] == 2) {
			marker = new MarkerWithLabel({
				position : new google.maps.LatLng(loc[0], loc[1]),
				map : map,
				icon : '../images/blue_truck.png',
				labelContent : loc[2],
				labelAnchor : new google.maps.Point(22, 0),
				labelClass : "labels",
				labelStyle : {
					opacity : 0.75
				}
			});

			google.maps.event.addListener(marker, "mouseover", (function(marker, i) {
				return function() {
					infowindow.setContent(locations[i].split(',')[4] + ' <br>' + locations[i].split(',')[3] + ' km/h'+ ' <br>' + locations[i].split(',')[7]);
					infowindow.open(map, marker);
				}
			})(marker, i));
		}
	}

	/*
	 * map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(document.getElementById('legend'));
	 * 
	 * var legend = document.getElementById('legend');
	 * 
	 * var name = 'Borderou activ'; var icon = '../images/red_truck.png'; var
	 * div = document.createElement('div'); div.innerHTML = '<table
	 * cellpadding="1"><tr><td><img src="../images/red_truck.png"></td><td  valign="center">' +
	 * name + '</td></tr></table>'; legend.appendChild(div);
	 * 
	 * var name = 'Borderou terminat'; var icon = '../images/green_truck.png';
	 * var div = document.createElement('div'); div.innerHTML = '<table
	 * cellpadding="1"><tr><td><img src="../images/green_truck.png"></td><td  valign="center">' +
	 * name + '</td></tr></table>'; legend.appendChild(div);
	 * 
	 * var name = 'Fara borderou'; var icon = '../images/blue_truck.png'; var
	 * div = document.createElement('div'); div.innerHTML = '<table
	 * cellpadding="1"><tr><td><img src="../images/blue_truck.png"></td><td  valign="center">' +
	 * name + '</td></tr></table>'; legend.appendChild(div);
	 */

}
