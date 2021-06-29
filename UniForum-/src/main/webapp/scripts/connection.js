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

function connection(username, password){

    var data = JSON.stringify([username,password]);

    postServerData("ws/User/connection",data,function(response){
        if (response){
            window.location.href="index2.html";
            
        }
        else{
            alert("Error: the username and the password doesn't match");
        }
    })
}

/*function callDone(result){
	var templateExample = _.template($('#templateUsername').html());

	var html = templateExample({
		"attribute":JSON.stringify(result)
	});

	$("#result").append(html);
}*/


$(document).ready(function () {
    console.log("coucou");
    $("#login-btn").click(function () { 
        var username = $("#Log in").val();
        var password = $("#password").val();
        connection(username, password);
        window.location.href="index2.html";
        //callDone(username);

    });

    
});