var MAZE = (function () {
    "use strict";

    // the game state
    var state = "";
    // where we display state to the user
    var status;

    // activated to start the game
    var start = function () {
        state = "started";
        status.text("Good luck!");
        $("div.boundary").removeClass("youlose");
    };

    // activated when game is lost
    var lost = function () {
        if (state === "started") {
            state = "lost";
            $("div#maze div.boundary").addClass("youlose");
            status.text('Sorry, you lost :[ Click "S" to restart');
        }
    };

    // activated when the game is won
    var won = function () {
        if (state === "started") {
            state = "won";
            status.text('You win! :] Click "S" to restart');
        }
    };

    // active while going through the maze (not outside)
    var notCheating = function (evt) {
        evt.stopPropagation();
    };

    // hook into the DOM on window load
    $(function () {
        status = $("#status");
        $("div#start").click(start);
        $("div.boundary, body").mouseover(lost);
        $("div#end").mouseover(won);
        $("div#maze").mouseover(notCheating);
    });
    
    // return code as module
    return {
        'start': start,
        'lost': lost,
        'won': won
    };
})();


