$(document).ready(

function() {
	$('#filiale').on('change', function() {
		waitingDialog({});
		var filiala = $('#filiale').val();

		selectedFiliala = $("#filiale option:selected").text();

		$.get('/FlotaWeb/GpsInactiv', {
			filialaSel : filiala
		}, function(responseText) {

			$('#listModuleInactive').html(responseText);

			closeWaitingDialog();

			

		});

	});

});
