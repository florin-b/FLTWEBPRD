/**
 * 
 */

var selectedFiliala, selectedSofer, selectedDocument;
var strOptionsInfo = "";

$(document).ready(

function() {
	$('#filiale').on('click', function() {
		waitingDialog({});
		var filiala = $('#filiale').val();

		selectedFiliala = $("#filiale option:selected").text();

		$.get('/FlotaWeb/getSoferi.do', {
			filialaSel : filiala
		}, function(responseText) {

			$('#listSoferi').html(responseText);
			hideOptions();
			closeWaitingDialog();

		});

	});

	$('#showDocumente').on('click', function() {

		var soferSel = $('#listSoferi').children('select[name="soferi"]').val().toString();

		var dataStart = $('#datepickerStart').val();
		var dataStop = $('#datepickerStop').val();

		waitingDialog({});

		$.post('/FlotaWeb/getBorderouri.do', {
			codSofer : soferSel,
			startInterval : dataStart,
			stopInterval : dataStop
		}, function(response) {
			$('#listDocumente').html(response);
			document.getElementById('block_documente').style.visibility = "visible";
			closeWaitingDialog();

		});

	});

	$('#listSoferi').on('click', function() {

		document.getElementById('datepickerStart').value = getFirstDayOfMonth();

		document.getElementById('datepickerStop').value = getToday();

		document.getElementById('intervalDiv').style.visibility = "visible";

		document.getElementById('block_documente').style.visibility = "hidden";
		document.getElementById('rezultatActivitate').style.visibility = "hidden";
		document.getElementById('showMapButton').style.visibility = "hidden";
		document.getElementById('hartaTraseu').style.visibility = "hidden";
		clearSelectedOptionsInfo();

		selectedSofer = $("#listSoferi option:selected").text();

	});

	$('#listDocumente').on('click', function() {

		selectedDocument = $("#listDocumente option:selected").text();

		composeSelectedOptionsInfo();

		document.getElementById('rezultatActivitate').style.visibility = "hidden";
		document.getElementById('showMapButton').style.visibility = "hidden";
		document.getElementById('hartaTraseu').style.visibility = "hidden";

	});

	$('#showActivitate').on('click', function() {

		var documentSel = $('#listDocumente').children('select[name="borderouri"]').val().toString();

		selectedDocument = $("#listDocumente option:selected").text();

		waitingDialog({});

		$.post('/FlotaWeb/getEvenimenteBorderou.do', {
			codBorderou : documentSel
		}, function(response) {

			$('#rezultatActivitate').html(response);
			document.getElementById('rezultatActivitate').style.visibility = "visible";
			document.getElementById('showMapButton').style.visibility = "visible";

			closeWaitingDialog();

			composeSelectedOptionsInfo();

		});

	});

	$('#showMapButton').on('click', function() {

		waitingDialog({});

		$.post('/FlotaWeb/getHartaBorderou.do', {

		}, function(response) {

			closeWaitingDialog();

			document.getElementById('hartaTraseu').style.visibility = "visible";
			getHartaTraseuBorderou(response);

		});

	});

	$('#showLess').on('click', function() {

		if (document.getElementById('optionsRow').style.display != 'none') {
			document.getElementById('optionsRow').style.display = 'none';
			document.getElementById('selectedOptions').style.visibility = "visible";

			document.getElementById('selectedOptions').innerHTML = strOptionsInfo;

		} else {
			document.getElementById('optionsRow').style.display = 'block';
			document.getElementById('selectedOptions').style.visibility = "hidden";

		}
	});

});

function composeSelectedOptionsInfo() {
	strOptionsInfo = selectedFiliala + ", " + selectedSofer + ", " + selectedDocument;
}

function clearSelectedOptionsInfo() {
	strOptionsInfo = "";
	document.getElementById('selectedOptions').innerHTML = "";
}

function hideOptions() {
	document.getElementById('intervalDiv').style.visibility = "hidden";
	document.getElementById('block_documente').style.visibility = "hidden";
	document.getElementById('rezultatActivitate').style.visibility = "hidden";
	document.getElementById('showMapButton').style.visibility = "hidden";
	document.getElementById('hartaTraseu').style.visibility = "hidden";
	clearSelectedOptionsInfo();
}

function getToday() {
	var today = new Date().toJSON().slice(0, 10).split('-');
	return today[2] + "-" + today[1] + "-" + today[0];
}

function getFirstDayOfMonth() {
	var today = new Date().toJSON().slice(0, 10).split('-');
	return "01-" + today[1] + "-" + today[0];
}
