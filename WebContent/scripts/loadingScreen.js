/**
 * 
 */

$(document).ready(function() {

	$("#loadingScreen").dialog({
		autoOpen : false,
		dialogClass : "loadingScreenWindow",
		closeOnEscape : false,
		draggable : false,
		width : 460,
		minHeight : 60,
		modal : true,
		buttons : {},
		resizable : false,
		open : function() {
			$('body').css('overflow', 'hidden');
		},
		close : function() {
			$('body').css('overflow', 'auto');
		}
	});
});
function waitingDialog(waiting) {
	$("#loadingScreen").html(
			waiting.message && '' != waiting.message ? waiting.message
					: 'Se incarca...');
	$("#loadingScreen").dialog('option', 'title',
			waiting.title && '' != waiting.title ? waiting.title : 'Asteptati');
	$("#loadingScreen").dialog('open');
}
function closeWaitingDialog() {
	$("#loadingScreen").dialog('close');
}
