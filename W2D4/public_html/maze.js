$(function () {
    "use strict";
    var state = "";
    var status = $("#status");

    $("div.boundary, body").mouseover(function () {
        if (state === "started") {
            state = "lost";
            $("div#maze div.boundary").addClass("youlose");
            status.text("Sorry, you lost :[ Click S to restart");
        }
    });

    $("div#maze").mouseover(function (evt) {
        evt.stopPropagation();
    });

    $("div#end").mouseenter(function () {
        if (state === "started") {
            state = "won";
            status.text("You win! :] Click S to restart");
        }
    });

    $("div#start").click(function () {
        state = "started";
        status.text("Good luck!");
        $("div.boundary").removeClass("youlose");
    });

});