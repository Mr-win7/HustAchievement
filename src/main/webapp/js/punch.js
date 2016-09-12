var punch = document.createElement('div');
punch.className = 'punch';
punch.id = 'punch';
punch.innerHTML = '<form name="punchForm" action="" method="POST">\
		                <span class="title"></span>\
		                <label><span>日期</span><input type="text" name="date"></label>\
		                <label><span>时间</span><input type="text" name="time"></label>\
		                <a class="btn" id="punch-btn" onclick="punchSubmit();"></a>\
		            </form>';


function showPunch() {
	var punchWrapper = document.createElement('div');
	punchWrapper.id = 'punch-wrapper';
	punchWrapper.appendChild(punch);
	document.body.appendChild(punchWrapper);
	punchWrapper.onclick = function(e) {
		if (e.target === punchWrapper) {
			quit();
		}
	};

	getTime();
}

function getTime() {
	var now = new Date();

	var date = punchForm.date;
	date.value = now.getFullYear() + '-' + (now.getMonth() + 1) + '-' + now.getDate();

	var time = punchForm.time;
	time.value = now.getHours() + ':' + now.getMinutes() + ':' + now.getSeconds();
}

function punchSubmit() {
	var now = new Date();

	var date = punchForm.date;
	if (!date.value) {
		date.value = now.getFullYear() + '-' + (now.getMonth() + 1) + '-' + now.getDate();
	}

	var time = punchForm.time;
	if (!time.value) {
		time.value = now.getHours() + ':' + now.getMinutes() + ':' + now.getSeconds();
	}

	var punchWrapper = document.getElementById('punch-wrapper');
	if (now.getHours() >= 6 && now.getHours() < 8) {
		$.ajax({
			type: 'POST',
			url: ' /achievement/user/user/signin'
		}).done(function(res) {
			var punchSuccess = document.createElement('div');
			punchSuccess.className = 'punch';
			punchSuccess.id = 'punch-success';
			punchSuccess.innerHTML = '<span class="title"></span>\
							            <p>第' + res.sum + '天早起打卡成功！你真是个早睡早起的健康宝宝，快去炫耀刺激一下你的好盆友吧！</p>\
							            <a class="btn" id="quit" onclick="quit();">退出</a>\
							            <a class="btn" id="share" onclick="">分享</a>';
			if (document.getElementById('punch')) {
				document.getElementById('punch').remove();
				punchWrapper.appendChild(punchSuccess);
			}
			if (res.sum == 21) {
				$.ajax({
					type: 'GET',
					url: '/achievement/user/user/achieve',
					contentType: 'application/json',
					data: JSON.stringify({
						achievementName: '连续21天早起打卡'
					})
				}).done(function() {
					$('#连续21天早起打卡').removeClass('s-place-notactive')
						.addClass('s-place-active');
				}).fail(errHandler);
			}
		}).fail(errHandler);
	} else {
		alert('6:00-7:59间打卡有效')
	}
}

function quit() {
	var punchWrapper = document.getElementById('punch-wrapper');
	if (punchWrapper) {
		document.body.removeChild(punchWrapper);
	}
}