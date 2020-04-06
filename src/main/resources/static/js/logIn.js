$(document).ready(function () {

    $("#log_in_button").click(function() {

        console.log("Log in clicked");
		
		var email = $('#email').val();
		var password = $('#password').val();
		
		if(!email || !password) {
			alert("This field is mandatory.");
			return;
		}
		
		var param = {
				email: email,
				password: password
		}
		
		$.ajax({
	        url: "/logIn",
	        method: "POST",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {	    	
	    	document.cookie = "accesstoken=" + data.data.token;
	    	window.location.href = '/';
	    }, function(err) {
	    	alert("Please check your information again.");
	    	window.location.reload();
	    });
        return false;
        
    });

});
