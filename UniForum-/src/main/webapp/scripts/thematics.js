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

function getQuestions() {
  $.ajax({
    	type: "POST",
      url: "ws/Message/create",
      data: JSON.stringify(newQuestion),
      contentType : "application/json; charSet=UTF-8",
      dataType: "json",
      success: function(res){
          calldone(res);
      }
  }).done(function(res){
      console.log("done "+res);
  });
}
/*
function callDone(result){
	var templateThematic = _.template($('#templateThematic').html());

	var html = templateThematic({
		"attribute":JSON.stringify(result)
	});

	$("#result").append(html);
}*/

$(document).ready(function (){
  $("#sport-btn").click(function(){
	  //getServerData("ws/Thematics/getall",callDone);
	  var title = $("#Title").val();
	  //callDone(title);
	  window.location.href = "threads.html";
	});
});

