$(document).ready(function () {

	$("#email_check_button").click(function () {

		console.log("Email duplication check");

		var email = $('#email').val();

		var param = {
			email: email
		}

		$.ajax({
			url: "/checkEmail",
			method: "POST",
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(param)
		}).then(function (data) {
			document.cookie = "checktoken=" + data.data.token;
			alert("Email available");
		}, function (err) {
			alert("Please check for email duplication again.");
			window.location.reload();
		});
		return false;
	});

	$("#sign_up_button").click(function () {

		console.log("Sign up clicked");

		var email = $('#email').val();
		var name = $('#name').val();
		var birthday = $('#birthday').val();
		var password = $('#password').val();
		var reentered_password = $('#reentered_password').val();
		var sex = document.querySelector('input[name="sex"]:checked').value

		if (!email || !password || !name || !birthday || !reentered_password || !sex) {
			alert("This field is mandatory.");
			return;
		}

		if (document.cookie.value == null) {
			alert("Please check for email duplication.");
			return;
		}

		if (password != reentered_password) {
			alert("Please confirm your password.");
			return;
		}

		var todaysDate = new Date().parse;
		var birthdayInt = birthday.parse;
		if (todaysDate - birthdayInt < 80000) {
			alert("You don't meet the minimum age requirement for our Terms & Conditions.");
			return;
		}

		var param = {
			email: email,
			name: name,
			birthday: birthday,
			password: password,
			sex: sex
		}

		$.ajax({
			url: "/signUp",
			method: "POST",
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(param)
		}).then(function (data) {
			alert("Sign up successful");
			window.location.href = '/logIn';
		}, function (err) {
			alert("Please check your information again.");
			window.location.reload();
		});
		return false;

	});

});
