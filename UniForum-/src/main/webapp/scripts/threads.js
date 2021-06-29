function callDone(result){
	var templateThematic = _.template($('#templateThematic').html());

	var html = templateThematic({
		"attribute":JSON.stringify(result)
	});

	$("#result").append(html);
}

$(document).ready(function () {
  $("#answers-btn").click(function () {
    window.location.href="discussion.html";
    
  });
});