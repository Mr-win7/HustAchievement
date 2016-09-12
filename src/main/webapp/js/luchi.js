function show(obj) {
	var index = parseInt(obj.getAttribute('index'), 10);
	var path = document.getElementsByClassName('path');
	if (/active/.test(obj.className)) {
		obj.classList.remove('btn-active');
		path[index].classList.remove('path-active');
		path[index].classList.add('path-notactive');
	} else {
		obj.classList.add('btn-active');
		path[index].classList.add('path-active');
		path[index].classList.remove('path-notactive');
	}
}

$(function() {
	$.ajax({
		type: 'POST',
		url: '/achievement/user/user/schedule',
		contentType: 'application/json',
		data: JSON.stringify({
			category: '路痴拯救'
		})
	}).done(function(achievements) {
		if (achievements.length != 0) {
			achievements.forEach(function(ach) {
				$('#' + ach.name).removeClass('place-notactive')
					.addClass('place-active');
			});
		}
	}).fail(errHandler);
});