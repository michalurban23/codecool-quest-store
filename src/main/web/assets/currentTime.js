function updateClock() {

    var currentTime = new Date();
    var min = currentTime.getMinutes();
    var sec = currentTime.getSeconds();
    var hour = currentTime.getHours();

    min = (min < 0 ? "0" : "") + min;
    sec = (sec < 0 ? "0" : "") + sec;

    var currentTimeString = hour + ":" + min + ":" + sec;

    document.getElementById("clock").innerText = currentTimeString;
}

function loopTime() {

    setInterval(updateClock, 1000);
}