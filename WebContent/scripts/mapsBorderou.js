function getStareBorderou(strLocations) {

	var locations = strLocations.split('#');
	var map;
	var mapOptions = {
		zoom : 7,
		center : new google.maps.LatLng(46.112984, 24.833889),
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById('hartaTraseu'),
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
			labelAnchor : new google.maps.Point(22, 0),
			labelClass : "labels",
			labelStyle : {
				opacity : 0.75
			}
		});

		google.maps.event.addListener(marker, "click", (function(marker, i) {
			return function() {
				infowindow.setContent(locations[i].split(',')[2]);
				infowindow.open(map, marker);
			}
		})(marker, i));

	}

}

function getHartaTraseuBorderou(strLocations) {

	var dateTraseu = strLocations.split('--');

	var istoricTraseu = dateTraseu[0].split('#');
	var pozitieClienti = dateTraseu[1].split('#');

	var mapCenter = parseInt(istoricTraseu.length / 2);

	var traseu = [];
	var loc, position, centerLocation;
	for (var i = 0; i < istoricTraseu.length - 1; i++) {
		loc = istoricTraseu[i].split(',');
		position = new google.maps.LatLng(loc[0], loc[1]);

		if (i == mapCenter) {
			centerLocation = new google.maps.LatLng(loc[0], loc[1]);

		}

		traseu.push(position);
	}

	var map = new google.maps.Map(document.getElementById('hartaTraseu'), {
		zoom : 8,
		center : centerLocation,
		mapTypeId : google.maps.MapTypeId.TERRAIN
	});

	var traseuPath = new google.maps.Polyline({
		path : traseu,
		geodesic : true,
		strokeColor : '#FF0000',
		strokeOpacity : 1.0,
		strokeWeight : 3
	});

	var pozitieCamionMarker = new MarkerWithLabel({
		position : new google.maps.LatLng(loc[0], loc[1]),
		labelAnchor : new google.maps.Point(22, 0),
		labelClass : "labels",
		icon : '../images/truck_icon.png',
		labelStyle : {
			opacity : 0.75
		}
	});

	var clientMarker;
	var infowindow = new google.maps.InfoWindow();

	var client;
	for (var j = 0; j < pozitieClienti.length; j++) {

		try {

			client = pozitieClienti[j].split(',');

			clientMarker = new MarkerWithLabel({
				position : new google.maps.LatLng(client[0], client[1]),
				labelContent : client[2],
				map : map,
				icon : '../images/customer.png',
				labelAnchor : new google.maps.Point(22, 0),
				labelClass : "labels",
				labelStyle : {
					opacity : 0.75
				}
			});

			google.maps.event.addListener(clientMarker, "mouseover", (function(
					clientMarker, j) {
				return function() {
					infowindow.setContent(pozitieClienti[j].split(',')[2]);
					infowindow.open(map, clientMarker);
				}
			})(clientMarker, j));
		} catch (err) {
			alert(err);
		}

	}

	traseuPath.setMap(map);
	pozitieCamionMarker.setMap(map);

}
