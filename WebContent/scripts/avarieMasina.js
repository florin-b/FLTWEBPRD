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

		document.getElementById('listRezultat').style.visibility = "hidden";
		document.getElementById('tipMesaj').style.visibility = "hidden";
		document.getElementById('showPosition').style.visibility = "hidden";

		selectedFiliala = $("#filiale option:selected").text();

		$.get('/FlotaWeb/getSoferi.do', {
			filialaSel : filiala
		}, function(responseText) {

			$('#listSoferi').html(responseText);
			closeWaitingDialog();

		});

	});

	$('#listSoferi').on('click', function() {
		waitingDialog({});
		selectedSofer = $("#listSoferi option:selected").val();

		document.getElementById('listRezultat').style.visibility = "hidden";
		document.getElementById('tipMesaj').style.visibility = "hidden";
		document.getElementById('showPosition').style.visibility = "hidden";

		$.get('/FlotaWeb/getClientiNelivrati.do', {
			codSofer : selectedSofer
		}, function(responseText) {

			closeWaitingDialog();

			if (responseText.length > 60) {
				document.getElementById('tipMesaj').style.visibility = "visible";
			} else {
				document.getElementById('tipMesaj').style.visibility = "hidden";
			}

			$('#listRezultat').html(responseText);

			document.getElementById('listRezultat').style.visibility = "visible";
			// document.getElementById('showPosition').style.visibility =
			// "visible";

		});

	});

	$('#sendSms').on('click', function() {

		selectedTemplate = $("#smsTemplate option:selected").text();

		$.get('/FlotaWeb/sendSms.do', {
			smsText : selectedTemplate
		}, function(responseText) {
			closeWaitingDialog();

		});

		alert('Mesaj expediat.');

	});

	$('#showPosition').on('click', function() {

	});

});
