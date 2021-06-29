function postServerData(url,data,callDone){
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        url: url,
        data: data,
        success: callDone
    });
}

function getServerData(url,callDone){
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        success: function (response) {
            callDone(response);
        }
    });
}

function callDone(result){
	var templateClick = _.template($('#templateClick').html());

	var html = templateClick({
		"attribute":JSON.stringify(result)
	});

	$("#result").append(html);
}

$(document).ready(function () {
	$("#click-btn").click(function () { 
		callDone('New on UniForum? Hurry to register!');
	});
});

