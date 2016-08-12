/**
 * 
 */

var selectedFiliala = "";
var timerRefresh;

$(document).ready(

function() {

	$('#filiale').click(function() {
		waitingDialog({});
		var filiala = $('#filiale').val();

		selectedFiliala = $("#filiale option:selected").text();

		$.get('/FlotaWeb/getMasini.do', {
			filialaSel : filiala,
			multiple : 1
		}, function(responseText) {

			$('#listMasina').html(responseText);

			closeWaitingDialog();

			document.getElementById('pozitieMasini').style.visibility = "hidden";

			if (responseText.indexOf('option') != -1) {
				$('#checkGroup').css('display', 'inline');
				$('#checkToate').prop('checked', false);
			} else {
				$('#checkGroup').css('display', 'none');
			}

		});

	});

	$('#showPozitie').on('click', function() {

		var masini = $('#listMasina').children('select[name="masini"]').val().toString();

		waitingDialog({});

		$.post('/FlotaWeb/getPozitieMasini.do', {
			listMasini : masini
		}, function(response) {

			closeWaitingDialog();
			getPozitieMasini(response);
			document.getElementById('pozitieMasini').style.visibility = "visible";
			window.scrollTo(0, document.body.scrollHeight);

		});

	});

	$('#checkToate').change(function() {

		$('#listMasina > #masini option').prop('selected', $(this).is(':checked'));

	});

	$('#refresh').change(function() {

		if ($(this).is(':checked')) {
			timerRefresh = setInterval(refreshPositions, 180000);
		} else {
			window.clearInterval(timerRefresh);
		}

	});

	$('#showLess').on('click', function() {

		if (document.getElementById('optionsRow').style.display != 'none') {
			document.getElementById('optionsRow').style.display = 'none';

			document.getElementById('selectedOptions').style.visibility = "visible";

			document.getElementById('refreshGroup').style.visibility = "visible";

			document.getElementById('selectedOptions').innerHTML = selectedFiliala;

		} else {
			document.getElementById('optionsRow').style.display = 'inline';
			document.getElementById('selectedOptions').innerHTML = "";

			document.getElementById('refreshGroup').style.visibility = "hidden";

			$("#refresh").prop("checked", false);
			window.clearInterval(timerRefresh);

		}
	});

});

function refreshPositions() {
	$('#showPozitie').click();
}

function clearSelectedOptionsInfo() {
	document.getElementById('selectedOptions').innerHTML = "";
}
