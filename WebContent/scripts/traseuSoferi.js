/**
 * 
 */

var selectedFiliala, selectedSofer, selectedDocument;
var strOptionsInfo = "";
var selectedMasina = "";

$(document).ready(

function() {
	$('#filiale').on('click', function() {
		waitingDialog({});
		var filiala = $('#filiale').val();

		selectedFiliala = $("#filiale option:selected").text();

		$.get('/FlotaWeb/getMasini.do', {
			filialaSel : filiala,
			multiple : 0
		}, function(responseText) {

			$('#listMasina').html(responseText);

			closeWaitingDialog();

		});

	});

	$('#listMasina').on('click', function() {
		selectedMasina = $("#listMasina option:selected").text();
		document.getElementById('intervalDiv').style.visibility = "visible";

	});

	$('#showTraseu').on('click', function() {

		var dataStart = $('#datepickerStart').val() + " " + $('#timepickerStart').val();
		var dataStop = $('#datepickerStop').val() + " " + $('#timepickerStop').val();

		if (!(moment(dataStart, "DD-MM-YYYY HH:mm", false).isValid())) {
			alert('Data inceput interval invalida.');
			return;
		}

		if (!(moment(dataStop, "DD-MM-YYYY HH:mm", false).isValid())) {
			alert('Data sfarsit interval invalida.');
			return;
		}

		waitingDialog({});

		$.get('/FlotaWeb/TraseuSofer.do', {
			masina : selectedMasina,
			startInterval : dataStart,
			stopInterval : dataStop

		}, function(responseText) {

			closeWaitingDialog();

			var strResponse = responseText.split("@", 3);

			
			
			var sumar = strResponse[0];
			var traseu = strResponse[1];
			var opriri = strResponse[2];

			document.getElementById('sumarActivitate').style.visibility = "visible";
			$('#sumarActivitate').html(sumar);
			document.getElementById('hartaTraseu').style.visibility = "visible";
			getHartaTraseuInterval(traseu, opriri);

		});

	});

});
