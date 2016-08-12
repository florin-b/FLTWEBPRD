$(function() {
	$("#datepickerStart").datepicker({
		dateFormat : 'dd-mm-yy'
	});
	$("#datepickerStop").datepicker({
		dateFormat : 'dd-mm-yy'
	});

});

$(document).ready(function() {
	$('input.timepickerStart').timepicker({
		timeFormat : 'HH:mm',
		interval : 60,
		minTime : '00',
		maxTime : '23:00',
		defaultTime : '00',
		startTime : '00:00',
		dynamic : true,
		dropdown : true,
		scrollbar : true
	});
});


$(document).ready(function() {
	$('input.timepickerStop').timepicker({
		timeFormat : 'HH:mm',
		interval : 60,
		minTime : '00',
		maxTime : '23:00',
		defaultTime : '00',
		startTime : '00:00',
		dynamic : true,
		dropdown : true,
		scrollbar : true
	});
});
