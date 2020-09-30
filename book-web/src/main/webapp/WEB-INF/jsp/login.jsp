<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Book Library</title>
<link href="/webjars/bootstrap/3.3.6/css/bootstrap.css" rel="stylesheet" />
</head>
<body class="body">
	<form:form method="post" modelAttribute="userModel">
		<div class="form" style="width:500px">
			<h1>Login</h1>

			<fieldset class="form-group">
				<form:label path="username">User Name</form:label>
				<form:input path="username" type="text" class="form-control"
					required="required"></form:input>
			</fieldset>

			<fieldset class="form-group">
				<form:label path="password">Password</form:label>
				<form:input path="password" type="password" class="form-control"
					required="required"></form:input>
			</fieldset>

			<input type="submit" class="btn btn-success" value="Login" />

		</div>
	</form:form>
	<script src="/webjars/bootstrap/3.3.6/js/bootstrap.js" />
	<script src="/webjars/jquery/1.9.1/jquery.js" />
</body>
</html>