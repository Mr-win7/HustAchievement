$(function() {
	$.ajax({
		type: 'POST',
		url: '/achievement/user/user/schedule',
		contentType: 'application/json',
		data: JSON.stringify({
			category: '流浪生活'
		})
	}).done(function(achievements) {
		achievements.forEach(function(ach) {
			$('#' + ach.name).removeClass('place-notactive')
				.addClass('place-active');
		});
	}).fail(errHandler);

	$('.shuaye').click(function(e) {
		var date = new Date();
		var chour = date.getHours();
		if (chour > 1 && chour < 6) {
			$.ajax({
				type: 'GET',
				url: '/achievement/user/user/achieve',
				data: {
					achievementName: '刷夜一次'
				}
			}).done(function() {
				$('#刷夜一次').removeClass('shuaye-notactive')
					.addClass('shuaye-active');
			}).fail(errHandler);
		} else {
			warn('在凌晨1点至6点之间点击有效！\n现在是：' + chour + ':' + date.getMinutes())
		}
	})
});