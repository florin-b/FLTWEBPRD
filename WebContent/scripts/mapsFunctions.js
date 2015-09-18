function getPozitieMasini(strLocations) {

	var locations = strLocations.split('#');

	var mapCenterPosition = parseInt(locations.length / 2);

	var centerArrayCoord = locations[mapCenterPosition].split(',');
	var mapCenterPosition = new google.maps.LatLng(centerArrayCoord[0],
			centerArrayCoord[1]);

	var map;
	var mapOptions = {
		zoom : 8,
		center : mapCenterPosition,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById('pozitieMasini'),
			mapOptions);
	var infowindow = new google.maps.InfoWindow();
	var marker;
	var loc;
	var carInfo;

	for (i = 0; i < locations.length; i++) {

		loc = locations[i].split(',');

		marker = new MarkerWithLabel({
			position : new google.maps.LatLng(loc[0], loc[1]),
			map : map,
			labelContent : loc[2],
			labelAnchor : new google.maps.Point(22, 0),
			labelClass : "labels",
			labelStyle : {
				opacity : 0.75
			}
		});

		google.maps.event.addListener(marker, "mouseover",
				(function(marker, i) {
					return function() {
						infowindow.setContent(locations[i].split(',')[4]
								+ ' <br>' + locations[i].split(',')[3]
								+ ' km/h');
						infowindow.open(map, marker);
					}
				})(marker, i));
	}

}
