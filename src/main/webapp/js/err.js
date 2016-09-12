function errHandler(err){
	if(window.localStorage){
		localStorage.errMessage = err.message;
		window.location.herf = './err.html';
	}else{
		alert(err.message);
	}
}