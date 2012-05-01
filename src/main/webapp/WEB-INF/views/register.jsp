<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta name="viewport"
	content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" />
<link rel="stylesheet" href="<c:url value="/resources/page.css" />"
	type="text/css" media="screen" />
<script type="text/javascript"
	src="<c:url value="/resources/jquery/1.6/jquery-1.6.1.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jquery-ui/jquery-ui-1.8.10.custom.min.js" />"></script>	
<script type="text/javascript">
	window.scrollTo(0, 1);
</script>

<c:url var="findStateCitiesURL" value="/cities" />
<c:url var="findStatesURL" value="/states" />

<!-- http://forum.springsource.org/showthread.php?110258-dual-select-dropdown-lists -->
<!-- http://api.jquery.com/jQuery.getJSON/ -->
<script type="text/javascript">
$(document).ready(function() { 
	$('#usStates').change(
			function() {
				$.getJSON('${findStateCitiesURL}', {
					stateName : $(this).val(),
					ajax : 'true'
				}, function(data) {
					var html = '<option value="">City</option>';
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].name + '">'
								+ data[i].name + '</option>';
					}
					html += '</option>';

					$('#city').html(html);
				});
			});
});
</script>

<script type="text/javascript">
	$(document).ready(
			function() {
				$.getJSON('${findStatesURL}', {
					ajax : 'true'
				}, function(data) {
					var html = '<option value="">State</option>';
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].name + '">'
								+ data[i].name + '</option>';
					}
					html += '</option>';

					$('#usStates').html(html);
				});
			});
</script>

<script type="text/javascript">
	$(document).ready(function(){
		$("#city").change(onSelectChange);
	});

	function onSelectChange() {
		var selected = $("#city option:selected");		
		var output = "";
		if(selected.val() != 0){
			output = "You selected City " + selected.text();
		}
		$("#output").html(output);
	}
</script>

</head>

<body>
<c:if test="${not empty message}">
	<div class="${message.type.cssClass}">${message.text}</div>
</c:if>

<c:url value="/signup" var="signupUrl" />
<form:form id="signup" action="${signupUrl}" method="post"
	modelAttribute="signupForm">
	<div class="formInfo">
	<h2>Sign Up</h2>
	<s:bind path="*">
		<c:choose>
			<c:when test="${status.error}">
				<div class="error">Unable to sign up. Please fix the errors
				below and resubmit.</div>
			</c:when>
		</c:choose>
	</s:bind>
	<p>Select a State</p>
	</div>


	<fieldset>
	<div class="multiple">
	
	<form:select id="usStates" path="usState">
	</form:select> 
	
	<form:select id="city" path="city">
		<form:option value="">City</form:option>
	</form:select>
	
	</div>
	</fieldset>


	<p>
	<button type="submit">Sign Up</button>
	</p>
</form:form>

	<div id="output">
		
	</div>


</body>
</html>
