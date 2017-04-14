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
			// hideOptions();
			closeWaitingDialog();

		});

	});

	$('#listSoferi').on('click', function() {

		selectedSofer = $("#listSoferi option:selected").val();
		document.getElementById('sumarActivitate').style.visibility = "hidden";

		document.getElementById('operatiiTableta').style.visibility = "visible";
		document.getElementById('codTableta').value = "";

		getTableteSoferi(selectedSofer);

	});

	$('#saveCod').on('click', function() {

		var codTableta = $('#codTableta').val();

		var codSofer = $("#listSoferi option:selected").val();

		var codOperatie = '';

		if (document.getElementById('aloca').checked)
			codOperatie = 'aloca';

		if (document.getElementById('sterge').checked)
			codOperatie = 'sterge';

		if (codSofer == null) {
			alert('Selectati soferul.')
			return;
		}

		if (codOperatie.length == 0) {
			alert('Selectati operatia.')
			return;
		}

		if (codOperatie == 'aloca' && codTableta.length == 0) {
			alert('Introduceti codul tabletei.')
			return;
		}

		$.get('/FlotaWeb/operatiiTablete.do', {
			codSofer : codSofer,
			codTableta : codTableta,
			operatie : codOperatie
		}, function(responseText) {

			document.getElementById('codTableta').value = "";
			getTableteSoferi(codSofer);
		});

	});

});

function getTableteSoferi(selectedSofer) {

	$.get('/FlotaWeb/getTablete.do', {
		codSofer : selectedSofer
	}, function(responseText) {

		$('#sumarActivitate').html(responseText);
		document.getElementById('sumarActivitate').style.visibility = "visible";
		closeWaitingDialog();

	});

}
