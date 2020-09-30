<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Book Library</title>
<link href="/webjars/bootstrap/4.5.0/css/bootstrap.css" rel="stylesheet" />
</head>
<body class="body">

	<form:form method="post" modelAttribute="bookModel">
		<div class="form" style="width: 500px">
			<h1>Registration</h1>

			<fieldset class="form-group">
				<form:label path="bookName">Book Name</form:label>
				<form:input path="bookName" type="text" class="form-control"
					required="required"></form:input>
				<form:errors path="bookName" cssClass="text-warning" />
			</fieldset>

			<fieldset class="form-group">
				<form:label path="author">Author</form:label>
				<form:input path="author" type="text" class="form-control"
					required="required"></form:input>
			</fieldset>

			<input type="submit" class="btn btn-success" value="Submit" />

		</div>
	</form:form>
	<script src="/webjars/bootstrap/4.5.0/js/bootstrap.js" />
	<script src="/webjars/jquery/1.9.1/jquery.js" />
</body>
</html>