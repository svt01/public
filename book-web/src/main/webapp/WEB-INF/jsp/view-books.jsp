<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Book Library</title>
<link href="/webjars/bootstrap/4.5.0/css/bootstrap.css" rel="stylesheet" />
</head>
<body class="bpdy">
	<div class="form">
		<h1>Books List</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Book Name</th>
					<th>Author</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bookList}" var="book">
					<tr>
						<td>${book.bookName}</td>
						<td>${book.author}</td>
						<td><a type="button" class="btn btn-success"
								href="/update-book?id=${book.id}">Update 
						</td>
						<td><a type="button" class="btn btn-warning"
								href="/delete-book?id=${book.id}">Delete 
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<br> <a href="/add-book" class="btn btn-success"> Add a Book</a>

	</div>
	<script src="/webjars/bootstrap/4.5.0/js/bootstrap.js" />
	<script src="/webjars/jquery/1.9.1/jquery.js" />
</body>
</html>