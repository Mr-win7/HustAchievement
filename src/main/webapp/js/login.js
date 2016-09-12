function loginCheck(){
    var studentNumber = login.studentNumber;
    if(!studentNumber.value){
        warn('hub账号不能为空');
        return false;
    }else if(!/^U201[0-9]{6}/.test(studentNumber.value)){
        warn('请输入有效的hub账号');
        return false;
    }

    var pwd = $('#pwd');
    if(!pwd.val()){
        warn('密码不能为空!');
        return false;
    }
    // else {
    //     login.password.value = faultylabs.MD5(pwd.value);
    // }
    // warn(Base64.encode(pwd.val()));
    $.ajax({
        type: 'POST',
        url: '/achievement/user/user/login',
        contentType: 'application/json',
        data: JSON.stringify({
            "studentNumber": studentNumber.value,
            "password": Base64.encode($('#pwd').val())
        })
    }).done(function(){
        window.location.href = './chooseMap.html';
    }).fail(errHandler);
}
function warn(text){
    return alert(text);
}