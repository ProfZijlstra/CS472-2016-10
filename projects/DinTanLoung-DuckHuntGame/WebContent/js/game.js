/*
 * Game object
 * Tan Luong
 * 985408
 */
function Game(playerId, difficulty) {
	this.playerId = playerId;
	this.score = 0;
	
	// Lives and Bullets (For future version)
	this.lives = 1000;
	this.bullet_count = 100;

	// Set the difficulty - easy by default
	if (typeof (difficulty) === "undefined") {
		this.speed = this.difficulty.easy;
	} else {
		this.speed = this.difficulty[difficulty];
	}

	// Start drawing ducks
	this.timer = this.drawDucks();
	
	this.drawPlayers();
}

// Maps difficulty to speed
Game.prototype.difficulty = {
	easy : 8000,
	medium : 4000,
	hard : 2500
}

// draw Ducks. Waiting a bit then draw again
Game.prototype.drawDucks = function() {
	var self = this;

	// Do this again in a little while...
	return setInterval(function() {
		// End the game if we've run out of lives
		if (self.lives <= 0) {
			// Game over man
			self.gameOver();
		} else {
			// Keep going!
			$.get("duck", {
				"action" : "list"
			}).done(
					function(data) {
						$.each(data.Ducks, function(idx, obj) {
							var duck = new Duck(self, obj.id, obj.top,
									obj.left, obj.direction);
						});
					})
			.fail(function(xhr, status, exception) {
				console.log(xhr, status, exception);
			});
		}
	}, this.speed + 2000);

}

Game.prototype.drawPlayers = function() {
	// Get the crosshair
	var player = $("#" + this.playerId);
	
	// self pattern
	var self = this;

	// Get multi players with current positions
	setTimeout(function() {
		$.get("position", {
			'x' : player.position().left,
			'y' : player.position().top
		}).done(function(data) {
			var score = '';

			// Create names array
			var names = new Array();

			$.each(data, function(name, obj) {
				
				// add name to array
				names.push(name);
				
				if (name != self.playerId)
				{
					var dude = $("#" + name);
					if (!dude[0]) {
						
						var div = $("<div>", {
							"id" : name,
							"class" : "crosshair"
						});
						div.css("left", obj.x + "px");
						div.css("top", obj.y + "px");
						
						// Append to #game
						$("#game").append(div);
						
						// Reset the game to synchronize the ducks
						self.reset();
					} else {
						if (dude.position().left != obj.x || dude.position().top != obj.y)
						{
							dude.css("left", obj.x + "px");
							dude.css("top", obj.y + "px");
						}						
					}					
				}
				else {
					// Update score for current player
					$("#yourscore").text(obj.score);
				}
				
				score += name + ": " + obj.score + "&nbsp;&nbsp;&nbsp;";
			});

			// Update the scores for all players
			$('#score').html(score);

			// Remove inactive players
			$('.crosshair').each(function(idx, crosshair) {
				var existed = false;
				for (var i = 0; i < names.length; i++) {
					if ($(crosshair).attr("id") == names[i]) {
						existed = true;
						break
					}
				}

				// remove if not existed
				if (!existed) {
					$(crosshair).remove();
				}
			});
			
			// Repeat if no error
			self.drawPlayers();
		})
		.fail(function(xhr, status, exception) {
			console.log(xhr, status, exception);
		});
	}, 33); //33 frames per second
}

// Reset the game
Game.prototype.reset = function() {
	clearInterval(this.timer);
	this.timer = this.drawDucks();
}

// Intent for single mode
Game.prototype.addScore = function(points) {
	this.score += points;
	$('#score').text(this.score);
}