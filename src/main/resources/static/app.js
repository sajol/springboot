var stompClient = null;

function setConnected(connected) {
    if(connected){
        invokeIrt();
    }
}


function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/feeds', function (message) {
            showFeeds(JSON.parse(message.body));
        });
    });
}


function showFeeds(feed) {
    if(feed.type == "BBC"){
        $("#bbc").append(getFeedBlock(feed));
    }else if(feed.type == "NYT"){
        $("#nyt").append(getFeedBlock(feed));
    }
}


function getFeedBlock(feed){
    return "<a href='"+ feed.url +"'>" +
           "<p>" + feed.description + "</p>" +
           "</a>";
}


function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}


function showGreeting(message) {
    $(".nws").append("<tr><td>" + message + "</td></tr>");
}

function invokeIrt() {
    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: '/irt',
        success: function (result) {
            console.log('initialized');
        },
        error: function (xhr, textStatus, errorThrown) {
        }
    });
}


$(function () {
    connect();
});