<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<html>
<head>
<title>Account</title>
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

</head>

<body>

	<h1>Thanks for signing up from ${signupForm.city},
		${signupForm.usState}</h1>

	<c:url value="/" var="homeURL" />
	<p>
		<a href="${homeURL}">Home</a>
	</p>

</body>
</html>
