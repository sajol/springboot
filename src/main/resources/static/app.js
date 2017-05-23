var stompClient = null;

function setConnected(connected) {

    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

/*function sendName() {
 stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
 }*/

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    /*$("form").on('submit', function (e) {
     e.preventDefault();
     });*/
    connect();
    /*$( "#send" ).click(function() { sendName(); });*/
});