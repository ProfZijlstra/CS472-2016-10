var GAME = (function () {
    var name = null;
    var player = null;
    var going = null;
    var intervalId = null;
    var x = 0;
    var y = 0;

    function init(n, p) {
        name = n;
        player = p;
        intervalId = setInterval(gameLoop, 33); // 30 frames per sec
    }
    
    function end() {
        clearInterval(intervalId);
    }

    function go(d) {
        going = d;
    }

    function stop() {
        going = null;
    }

    function ajaxFailure(xhr, status, exception) {
        console.log(xhr, status, exception);
    }

    function updatePositions(data) {
        $.each(data, function (name, obj) {
            var dude = $("#" + name);
            if (!dude[0]) {
                $("<div>"+name+"</div>").attr("id", name).addClass("dude")
                        .appendTo("#container");
            }
            dude.css("left", obj.x + "px");
            dude.css("top", obj.y + "px");
        });
        x = data[name].x;
        y = data[name].y;
    }

    function gameLoop() {
        switch (going) {
            case 37: // ArrowLeft
                x -= 1;
                if (x < 0) {
                    x = 0;
                }
                //player.css("left", x + "px");
                break;
            case 38: // ArrowUp
                y -= 1;
                if (y < 0) {
                    y = 0;
                }
                //player.css("top", y + "px");                            break;
                break;
            case 39: // ArrowRight
                x += 1;
                if (x > 700 - 50) {
                    x = 700 - 50;
                }
                //player.css("left", x + "px");
                break;
            case 40: // ArrowDown
                y += 1;
                if (y > 700 - 50) {
                    y = 700 - 50;
                }
                //player.css("top", y + "px");                            break;
                break;
        }
        $.get("positions", {'x': x, 'y': y})
                .done(updatePositions)
                .fail(ajaxFailure);
    }

    return {
        "init": init,
        "go": go,
        "stop": stop,
        "end": end
    };
})();

$(function () {
    var name = $("#name").val();
    GAME.init(name, $("#name"));

    $(window).keydown(function (evt) {
        switch (evt.keyCode) {
            case 37: // ArrowLeft
            case 38: // ArrowUp
            case 39: // ArrowRight
            case 40: // ArrowDown
                GAME.go(evt.keyCode);
                break;
        }
    });
    $(window).keyup(function (evt) {
        switch (evt.keyCode) {
            case 37: // ArrowLeft
            case 38: // ArrowUp
            case 39: // ArrowRight
            case 40: // ArrowDown
                GAME.stop();
                break;
        }
    });
});
