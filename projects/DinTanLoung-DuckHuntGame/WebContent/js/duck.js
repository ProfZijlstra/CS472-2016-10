/*
 * Duck object
 * Tan Luong
 * 985408
 */
function Duck(game, id, top, left, direction) {
	this.game = game;
	this.id = id;
	this.top = top;
	this.left = left;
	this.direction = direction;

	// Clone duck from template
	this.el = $("#duck-template").clone();

	// Set duck id
	this.el.attr("id", "duck" + id)
	this.el.attr("data-id", id);

	// Add a callback for when the Duck is clicked (shot!)
	var self = this;
	$(this.el).click(function(event) {
		// Will limit the bullet in the next version
		if (self.game.bullet_count > 0) {
			var id = $(this).data("id");
			// Update dead status for duck
			$.post("duck", {
				"playerid" : self.game.playerId,
				"id" : id
			}).done(function() {
				self.die();
			});
		}
	});

	// Duck fly
	this.fly();

	// Check if duck is still alive or died
	this.check();
}

// Some animation using a Timeout to make the Duck flap.
Duck.prototype.flap = function() {
	$(this.el).toggleClass("flap");

	// self pattern
	var self = this;

	// Do this again in 300 milliseconds
	this.flapTimer = setTimeout((function() {
		self.flap();
	}), 300);
}

// Display the Duck on the screen.
Duck.prototype.fly = function() {
	// console.log("draw");
	var self = this;
	$('#game').append(this.el)

	// Make the duck appear somewhere random along the page and just off the
	// screen
	$(this.el).css({
		top : this.top,
		left : this.left
	});
	// Append the element to the DOM, use the #game element

	// Start Flapping...
	this.flap();

	// ... and Fly!
	$(this.el).animate({
		left : "+=1600",
		top : self.direction > 0 ? "+=200" : "-=200"
	}, this.game.speed, "linear", function() {
		self.complete();
		self.remove();
	});
}

// Check status!
Duck.prototype.check = function() {
	var self = this;

	setTimeout(function() {
		$.get("duck", {
			"action" : "status",
			"id" : self.id
		}).done(function(data) {
			if (data == "0")
				self.die();
			else
				self.check();
		}).fail(function(xhr, status, exception) {
			console.log(xhr, status, exception);
		});
	}, 33);
}

// TODO: I've been shot!
Duck.prototype.die = function() {
	var self = this;

	// Add a .dead CSS class
	$(this.el).addClass("dead");

	// Stop flapping - clear the flapTimer
	clearTimeout(this.flapTimer);

	// Stop flying animations
	$(this.el).stop();
	// $(this.el).addClass("fall");

	$(this.el).animate({
		top : "+=700px"
	}, 1000, "linear", function() {
		// _this.game.addScore(100); // <<<<
		self.remove();
	});

}

// For future version
Duck.prototype.complete = function() {
	this.game.lives -= 1;
	return this;
}

// Remove Duck from DOM
Duck.prototype.remove = function() {
	$(this.el).remove();
	delete this;
}