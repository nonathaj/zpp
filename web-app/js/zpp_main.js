
$(document).ready(function(){
	$('.date-time-picker').datetimepicker({
		addSliderAccess: true,
		sliderAccessArgs: { touchonly: false }
		timeFormat: 'HH:mm z',
		timezone: 'MT',
		timezoneList: [ 
			{ value: 'ET', label: 'Eastern'}, 
			{ value: 'CT', label: 'Central' }, 
			{ value: 'MT', label: 'Mountain' }, 
			{ value: 'PT', label: 'Pacific' } 
		]
	});
});