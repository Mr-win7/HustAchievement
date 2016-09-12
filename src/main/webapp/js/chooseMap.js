var data = [
        {name: 'luchi', difficulty: 3},
        {name: 'chihuo', difficulty: 4},
        {name: 'xueba', difficulty: 5},
        {name: 'shenghuo', difficulty: 5}
    ];
function slide(index) {
    
    var difficulty = document.getElementById('difficulty');

    var slide = document.getElementsByClassName('slide');
    var ctrl = document.getElementsByClassName('ctrl')[0].getElementsByTagName('a');
    var next = index + 1, prev = index - 1;
    if(next >= slide.length){
        next = 0;
    } else if (prev < 0) {
        prev = slide.length - 1;
    }

    difficulty.innerHTML = '<div class="text">难&nbsp;&nbsp;&nbsp;&nbsp;度</div>';
    for (i = 0; i < slide.length; i++) {
        slide[i].classList.remove('slide-cur');
        slide[i].classList.remove('slide-left');
        slide[i].classList.remove('slide-right');
        slide[i].style.transition = 'all 0.6s ease';
        ctrl[i].classList.remove('active');
    }
    for(var i = 0;i < data[index].difficulty;i++){
        var point = document.createElement('div');
        point.className = 'point';
        difficulty.appendChild(point);
    }
    
    slide[next].classList.add('slide-right');
    slide[index].classList.add('slide-cur');
    slide[prev].classList.add('slide-left');
    ctrl[index].classList.add('active');
}

$(function(){
    var leaderboard = $('.leaderboard');
    var paihang = $('#paihang');
    var back = $('#back');
    paihang.click(function(){
        leaderboard.addClass('leaderboard-on');
    });
    back.click(function(){
        leaderboard.removeClass('leaderboard-on');
    });

    $.ajax({
        type: 'GET',
        url: '/achievement/achievement/achievement/top'
    }).done(function(res){
        var navigator = res.路痴拯救[0] ? res.路痴拯救[0].studentNumber + '  ' + res.路痴拯救[0].name : 'No one';
        var gourmet = res.吃货比拼[0] ? res.吃货比拼[0].studentNumber + '  ' + res.吃货比拼[0].name : 'No one';
        var scholar = res.学霸来了[0] ? res.学霸来了[0].studentNumber + '  ' + res.学霸来了[0].name : 'No one';
        var traveller = res.流浪生活[0] ? res.流浪生活[0].studentNumber + '  ' + res.流浪生活[0].name : 'No one';
        $('#navigator').html(navigator);
        $('#gourmet').html(gourmet);
        $('#scholar').html(scholar);
        $('#traveller').html(traveller);
        for(var i = 0; i < res.Top5.length; i++){
            $('#topuser-' + i + ' .name').html(res.Top5[i].name);
            completion('#topuser-' + i, res.Top5[i].percentage, 'height');
        }
    }).fail(errHandler);

    $.ajax({
        type: 'POST',
        url: '/achievement/user/user/schedule'
    }).done(function(res){
       completion('#progress',( res.length / 50) * 100, 'width') 
    }).fail(errHandler);
});

function completion(selector, num, dir){
    $(selector + ' .fill div').css(dir, num + '%');
    $(selector + ' .percent').html(num + '%');
}
