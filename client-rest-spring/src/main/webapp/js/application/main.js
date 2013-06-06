var presenter;

$(document).ready(function() {
	presenter = new MainPresenter();
	presenter.init();
});

function MainPresenter() {
	
	this.init = function() {
		$.ajax({
			url: 'rest/example/test',  
			success: this.handleTestString
		});
		
		$.ajax({
			url: 'rest/example/me',  
			success: this.handleGetUser
		});
	}
	
	this.handleTestString = function(data, textStatus, request) {
		$('#view').append('<h3>' + data.test + '</h3>');
	}
	
	this.handleGetUser = function(data, textStatus, request) {
		$('#view').append('<h3> Logged in as ' + data.name + '</h3>');
		$('#view').append('<h3> User is ' + data.role + '</h3>');
	}
}