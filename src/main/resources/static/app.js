var stompClient = null;

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
        stopSpinner(bbcSpinner);
        $(getFeedBlock(feed)).hide().prependTo("#bbc").fadeIn();
    }else if(feed.type == "NYT"){
        stopSpinner(nytSpinner);
        $(getFeedBlock(feed)).hide().prependTo("#nyt").fadeIn();
    }
}


function getFeedBlock(feed){
    return  "<tr>" +
            "<td class='col-xs-12'>" +
            "<a href='"+ feed.url +"' target='_blank'>" +
            feed.description +
            "</a>" +
            "</td>" +
            "</tr>";
}


function stopSpinner(spinner){
    if($(".nws div.spinner").length > 0){
        spinner.stop();
    }
}


function initializeSpinner(){
    var opts = {
        lines: 11,
        length: 7,
        width: 10,
        radius: 26,
        corners: 1,
        opacity: 0.25,
        rotate: 0,
        direction: 1,
        speed: 0.8,
        trail: 60,
        shadow: true,
        hwaccel: true,
        top: '50%',
        left: '50%'
    };
    var bbc = document.getElementById('bbc');
    bbcSpinner = new Spinner(opts).spin(bbc);

    var nyt = document.getElementById('nyt');
    nytSpinner = new Spinner(opts).spin(nyt);
}


$(document).ready(function () {
    connect();
    initializeSpinner();
});