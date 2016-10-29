$(function() {
	console.log("Welcome to Duck Hunt Game!");

	//Get player name
	var playerId = $("#playerId").val();

	// Moves the crosshair with the mousepointer
	$("#game").mousemove(function(event) {
		$('#' + playerId).css("left", event.pageX - 30)
		$('#' + playerId).css("top", event.pageY - 30)
	});

	// Menu click handler
	$("#menu").click(function() {
		$("#game-menu").toggle();
	});

	// Continue menu item click
	$("#continue").click(function() {
		$("#game-menu").toggle();
	});

	// Quit menu item click: remove user and return to login page
	$("#quit").click(function() {
		$.post("player").done(function() {
			window.location.href = ".";
		});
	});
	
	// Window Unload event
	$(window).bind('beforeunload', function(){
		$.post("player");
	});
	
	// Create a new game with difficulty "medium"
	var difficulty = "medium";
	var game = new Game(playerId, difficulty);

});