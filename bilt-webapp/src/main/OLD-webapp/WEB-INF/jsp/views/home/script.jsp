<script type="text/javascript">
    var stompClient = null;

    $(function() {
        /**
        $('#conversationDiv').hide();
        $( "#draggable8863c47b-a7e0-4f42-8689-3432106f15cc" ).hide();
        $( "#draggabledb442fda-cf92-4787-a776-74cac38800be" ).hide();

        $("#draggable8863c47b-a7e0-4f42-8689-3432106f15cc").draggable({
            containment: "parent",
            opacity: 0.35,
            revert: function (droppable) {
                return droppable && $("#draggable8863c47b-a7e0-4f42-8689-3432106f15cc").addClass('ui-draggable-reverted');
            },
            stop: function (event,ui) {
                if ($("#draggable8863c47b-a7e0-4f42-8689-3432106f15cc").hasClass('ui-draggable-reverted')) {
                    $("#draggable8863c47b-a7e0-4f42-8689-3432106f15cc").removeClass('ui-draggable-reverted');
                } else {
                   sendTileMovement("8863c47b-a7e0-4f42-8689-3432106f15cc",ui);
                }
            }
        });
        $("#draggable8863c47b-a7e0-4f42-8689-3432106f15cc").droppable({
            greedy: true,
            tolerance: 'touch',
         });

        $("#draggabledb442fda-cf92-4787-a776-74cac38800be").draggable({
            containment: "parent",
            opacity: 0.35,
            revert: function (droppable) {
                return droppable && $("#draggabledb442fda-cf92-4787-a776-74cac38800be").addClass('ui-draggable-reverted');
            },
            stop: function (event,ui) {
                if ($("#draggabledb442fda-cf92-4787-a776-74cac38800be").hasClass('ui-draggable-reverted')) {
                    $("#draggabledb442fda-cf92-4787-a776-74cac38800be").removeClass('ui-draggable-reverted');
                } else {
                    sendTileMovement("db442fda-cf92-4787-a776-74cac38800be",ui);
                }

            }
        });
        $("#draggabledb442fda-cf92-4787-a776-74cac38800be").droppable({
            greedy: true,
            tolerance: 'touch',
         });


        $("#connect").click(function() {
            connect();
        })

        $("#disconnect").click(function() {
            disconnect();
        })*/
    });

    function connect() {
        /**
        var socket = new SockJS('http://localhost:9080/mytiles/');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/tileUpdate', function(movement){
                updateTileMovement(JSON.parse(movement.body));
            });
        });
        */
    }

    function disconnect() {
        /**
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
        */
    }

    function setConnected(connected) {
        /**
        $('#connect').prop('disabled',connected);
        $('#disconnect').prop('disabled',!connected);
        $('#name').prop('disabled',connected);
        if (connected) {
            $('#conversationDiv').show();
            $( "#draggable8863c47b-a7e0-4f42-8689-3432106f15cc" ).show();
            $( "#draggabledb442fda-cf92-4787-a776-74cac38800be" ).show();

            $.get("http://localhost:9090/tileplacements/tileboard/07270496-d622-4351-a4df-d4f28b31fe5c/tilebag/7194d3ea-b2be-4e1f-b606-8d5867bb5a34", function(placements) {
                console.log('Getting current tile placements');
                $.each(placements, function(index, placement) {
                    console.log('Tileplacement: ' + placement);
                    updateTilePlacement(placement);
                });
                console.log('DONE: Getting current tile placements');
            });
        } else {
            $('#conversationDiv').hide();
            $( "#draggable8863c47b-a7e0-4f42-8689-3432106f15cc" ).hide();
            $( "#draggabledb442fda-cf92-4787-a776-74cac38800be" ).hide();
        }
        $('#response').html('');
        */
    }

    function sendTileMovement(id,ui) {
        /**
        var name = $('#name').val();
        var now = new Date();
        stompClient.send("/app/tile/move", {}, JSON.stringify({
            'tileBoardId': '07270496-d622-4351-a4df-d4f28b31fe5c',
            'tileBagId': '7194d3ea-b2be-4e1f-b606-8d5867bb5a34',
            'tileId': id,
            'tileUserId': name,
            'tileTimestamp': now.getTime(),
            'tileX': ui.position.left,
            'tileY': ui.position.top,
            'tileZ': 0
        }));
        */
    }

    function updateTileMovement(message) {
        /**
        var draggable = $("#draggable" + message.tileId);
        draggable.animate({
            top: message.tileY,
            left: message.tileX
        }, 200);

        var response = $('#response');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(message.tileId
                + ", " + message.tileUserId
                + ", " + message.tileTimestamp
                + ", " + message.tileX
                + ", " + message.tileY
                + ", " +   message.tileZ));
        response.append(p);
        */
    }

    function updateTilePlacement(placement) {
        /**
        var draggable = $("#draggable" + placement.tileId);
        draggable.animate({
            top: placement.y,
            left: placement.x
        }, 200);
        */
    }
</script>
