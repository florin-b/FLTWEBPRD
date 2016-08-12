function getHartaTraseuInterval(strResults) {

	var istoricTraseu = strResults.split('#');

	var mapCenter = parseInt(istoricTraseu.length / 2);

	var traseu = [];
	var loc, position, centerLocation;
	for (var i = 0; i < istoricTraseu.length - 1; i++) {
		loc = istoricTraseu[i].split(',');

		if (loc == '')
			continue;

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

	var lineSymbol = {
		path : google.maps.SymbolPath.CIRCLE,
		scale : 8,
		strokeColor : '#393'
	};

	var traseuPath = new google.maps.Polyline({
		path : traseu,
		geodesic : true,
		icons : [ {
			icon : lineSymbol,
			offset : '100%'
		} ],
		strokeColor : '#FF0000',
		strokeOpacity : 1.0,
		strokeWeight : 3
	});

	traseuPath.setMap(map);

	animateCircle(traseuPath);

}

function animateCircle(line) {
	var count = 0;
	window.setInterval(function() {
		count = (count + 1) % 200;

		var icons = line.get('icons');
		icons[0].offset = (count / 2) + '%';
		line.set('icons', icons);
	}, 20);
}
