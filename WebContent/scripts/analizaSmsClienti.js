/**
 * 
 */

var selectedFiliala, selectedSofer, selectedDocument;
var strOptionsInfo = "";

$(document).ready(

function() {

	$('#showDocumente').on('click', function() {

		var dataSms = $('#datepickerStart').val();

		waitingDialog({});

		$.post('/FlotaWeb/analizaSmsClienti.do', {
			dataSmsTrimis : dataSms,
		}, function(response) {
			$('#rezultatActivitate').html(response);
			closeWaitingDialog();

		});

	});
})
