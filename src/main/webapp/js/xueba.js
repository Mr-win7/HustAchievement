$(function() {
	$.ajax({
		type: 'POST',
		url: '/achievement/user/user/schedule',
		contentType: 'application/json',
		data: JSON.stringify({
			category: '学霸来了'
		})
	}).done(function(achievements) {
		achievements.forEach(function(ach) {
			$('#' + ach.name).removeClass('place-notactive')
				.addClass('place-active');
		});
	}).fail(errHandler);

	$('.library').click(function() {
		var date = new Date();
		var chour = date.getHours();
		var cminutes = date.getMinutes();
		if (chour >= 21 && chour <= 22) {
			if (cminutes >= 30 && cminutes <= 59 || chour == 22) {
				getLoction('听一次图书馆闭馆铃声');
			} else {
				warn('21:30-22:00打卡有效！');
			}
		} else {
			warn('21:30-22:00打卡有效！');
		}
	});
});