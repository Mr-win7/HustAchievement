function show(obj) {
	var index = parseInt(obj.getAttribute('index'), 10);
	var dining = document.getElementsByClassName('dining');
	if (/active/.test(obj.className)) {
		obj.classList.remove('btn-active');
		dining[index].classList.remove('dining-active');
		dining[index].classList.add('dining-notactive');
	} else {
		obj.classList.add('btn-active');
		dining[index].classList.add('dining-active');
		dining[index].classList.remove('dining-notactive');
	}
}

function showDetail(name) {

	var index;
	for (var i = 0; i < data.length; i++) {
		if (data[i].name == name) {
			index = i;
		}
	}

	var detail = document.createElement('div');
	detail.id = "detail-wrapper";
	detail.innerHTML = '<div class="detail">\
				            <div class="caption">\
				                <h1>BBB</h1>\
				                <ul>\
				                    <li class="desc"><p><span>[介绍]&nbsp;&nbsp;</span>' + data[index].desc + '</p></li>\
				                    <li class="addsress"><p><span>[地址]&nbsp;&nbsp;</span>' + data[index].address + '</p></li>\
				                    <li class="special"><p><span>[特色推荐]&nbsp;&nbsp;</span>' + data[index].special + '</p></li>\
				                </ul>\
				            </div>\
				            <a class="btn" onclick="getLocation(' + name + ')"></a>\
				        </div>';

	detail.onclick = function(e) {
		if (e.target === detail) {
			quit();
		}
	};

	quit();
	document.body.appendChild(detail);

}

function quit() {
	if (document.getElementById('detail-wrapper')) {
		document.body.removeChild(document.getElementById('detail-wrapper'));
	}
}

$(function() {
	$.ajax({
		type: 'POST',
		url: '/achievement/user/user/schedule',
		contentType: 'application/json',
		data: JSON.stringify({
			category: '吃货比拼'
		})
	}).done(function(achievements) {
		achievements.forEach(function(ach) {
			$('#' + ach.name).removeClass('place-notactive')
				.addClass('place-active');
		});
	}).fail(errHandler);
});