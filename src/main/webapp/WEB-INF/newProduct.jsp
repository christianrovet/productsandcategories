<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>New Product Page</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
    <script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="container">
		<a href="/">Home</a>
		<h1>New Product</h1>
		<form:form action="/products/new" method="post" modelAttribute="product">
		<p>
			<form:label path="name">Name:</form:label>
			<form:input path ="name"/>
		</p>
		<p>
			<form:label path="description"> Description:</form:label>
			<form:input path="description"/>
		</p>
		<p>
			<form:label path="price">Price:</form:label>
			<form:input type="number" path="price"/>
		</p>
		<input type="submit" value="Create"/>
		</form:form>
		<form:errors path="product.*"/>
	</div>
</body>
</html>