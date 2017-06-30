/**
 * 
 */

var selectedFiliala, selectedSofer, selectedDocument;
var strOptionsInfo = "";

$(document).ready(

function() {

	$('#showDocumente').on('click', function() {

		var dataSms = $('#datepickerStart').val();
		
		var filiala = $('#filiale').val();
		
		if (filiala == null){
			alert('Selectati filiala');
			return;
		}
		
		
		
		waitingDialog({});

		$.post('/FlotaWeb/analizaSmsClienti.do', {
			dataSmsTrimis : dataSms,
			filSel : filiala
		}, function(response) {

			var strResponse = response.split("#", 2);

			$('#rezultatActivitate').html(strResponse[0]);
			$('#sumarActivitate').html(strResponse[1]);
			closeWaitingDialog();

		});

	});
})
