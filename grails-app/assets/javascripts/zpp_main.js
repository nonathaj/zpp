//= require bootstrap
$(document).ready(function(){
	
	//Tell moment that we are in the us
	moment.locale("en-us");
	
	//Set up datepickers for all the elements
	$(".datetimepicker").datetimepicker({
		stepping: 15,
		sideBySide: true,
		showClose: true,
		locale: "en-us"
	});
	
	//items we need to reformat to be local dates
	$(".format-unix-date").each(function(index, e){
		var unix = $(e).text();
		var momentObj = moment.unix(unix);
		$(e).text( momentObj.format("h:mm A dddd, MMMM Do YYYY") );
	});
	
	//Enable popovers and toolstips
	$('[data-toggle="popover"]').popover();
	$('[data-toggle="tooltip"]').tooltip();
	
	//Format any forms to all be the same size
	$('.zpp-form').addClass('col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-8 col-xs-offset-2');
	
	//If we have any start-end date pickers, don't allow them to choose dates in the past
	$('#start-display').data("DateTimePicker").minDate(moment());
	$('#end-display').data("DateTimePicker").minDate(moment());

	//if we have any start-end date pickers, connect them to not allow their dates to incorrectly match
	$("#start-display").on("dp.change", function (e) {
		var endDisplay = $('#end-display').data("DateTimePicker");
		endDisplay.minDate(moment.max(e.date, moment()));

		$('#start').val(e.date.unix());
	});
	$("#end-display").on("dp.change", function (e) {
		var startDisplay = $('#start-display').data("DateTimePicker");
		startDisplay.maxDate(e.date);

		$('#end').val(e.date.unix());
	});
});