
/**
 * @param {the username of the new user} username 
 * @param {the password of the new password} password 
 */
function createNewUser(username, password){
	console.log("hey");
        
    var newUser = [username, password];
    $.ajax({
    	type: "POST",
        url: "ws/User/create",
        data: JSON.stringify(newUser),
        contentType : "application/json; charSet=UTF-8",
        dataType: "json",
        success: function(res){
            window.location.href="main.html";
        }
    }).done(function(res){
        console.log("done "+res);
        window.location.href="main.html";            
     });
 }


/**
 * Main
 */
$(document).ready(function () {
    console.log("coucou");

    $("#create-btn").click(function () { 
        var username = $("#Username").val();
        var password = $("#Password").val(); 
        window.location.href="main.html";
        
        createNewUser(username, password);
    });
    
});