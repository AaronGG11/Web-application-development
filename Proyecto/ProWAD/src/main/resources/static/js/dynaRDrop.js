$(document).ready(function() {
   $("#states").change(function() {
      var stateId = $(this).val();
      var s = '<option value="" selected disabled>Choose town</option>';
      if (stateId > 0) {
      	$.ajax({
        url : 'getTowns',
        data : {"stateId" : stateId},
        success : function(result) {
        	var result = JSON.parse(result);
        	for (var i = 0; i < result.length; i++) {
        		s += '<option value="' + result[i][0] + '">'+ result[i][1]+ '</option>';
        	}
        	$('#towns').html(s);
        }
       });
     }
     //reset data
     $('#towns').html(s);
  });
});