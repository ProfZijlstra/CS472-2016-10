<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Duck Hunt Game</title>
	
	<!-- Favicon -->
	<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
	<link rel="icon" href="favicon.ico" type="image/x-icon">
	
	<!-- css -->
	<link href="css/reset.css" type="text/css" rel="stylesheet" />
	<link href="css/main.css" type="text/css" rel="stylesheet" />
	
	<!-- JavaScript -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="js/duck.js" type="text/javascript"></script>
	<script src="js/game.js" type="text/javascript"></script>
	<script src="js/main.js" type="text/javascript"></script>
</head>
<body>

	<div id="score"></div>
	
	<div id="menu">Menu</div>
	
	<div id="actors">
		<figure id="duck-template" class="sprite duck right"></figure>
	</div>

	<div id="game">
		<input type="hidden" id="playerId" value="${ me.name }" />
		<div class="crosshair" id="${ me.name }"></div>
	</div>

	<div id="game-menu">
		<div id="game-menu-message">
			<h3>Game Menu</h3>

			<p>
				You Scored: <span id="yourscore">0</span>
			</p>

			<p id="continue">Continue</p>
			<p id="quit">Quit</p>
		</div>
	</div>
</body>
</html>