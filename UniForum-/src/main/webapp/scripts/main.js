function createNewDiscussion(thematic, question) {
  var newDiscussion = [thematic, question];
  $.ajax({
    type: "POST",
    url: "ws/Discussion/addDiscu/{thematicId}",
    data: JSON.stringify(newDiscussion),
    contentType : "application/json; charSet=UTF-8",
      dataType: "json",
      success: function(res){
          console.log("done "+res);
      }
  });
}

function createNewQuestion(title, description) {
  var newQuestion = [title, description];
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
* recover the thematics title
*/
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
/*
function callDone(result){
	var templateThematic = _.template($('#templateThematic').html());

	var html = templateThematic({
		"attribute":JSON.stringify(result)
	});

	$("#result").append(html);
}*/

$(document).ready(function () {
    console.log("tes");
    $("#submit-btn").click(function () { 
        console.log("coucou");
        
        var title = $("#Title").val();
        var description = $("#Description").val(); 
        if (title=="sport"||title=="social"||title=="trips"||title=="politics"||title=="music"||title=="television"||title=="health"||title=="home") {
          var discu = createNewDiscussion(title, description);
          createNewQuestion(discu, description);
          window.location.href="threads.html";
          //callDone(title);
          
          /*$("#submit-btn").click(funtion() {
            window.location.href="thematics.html";
          });*/
        }
        else {
          alert("Please enter a right thematic");
        }
    });
    
});