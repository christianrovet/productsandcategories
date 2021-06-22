<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Page</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
    <script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<div id = "wrapper">
		<h1>${category.name}</h1>
		<div id = "main">
			<div id = "products">
				<h3>Products:</h3>
				<ul>
					<c:forEach items = "${added}" var = "p">
				        <li>${p.name}</li>
		        </c:forEach>
				</ul>
			</div>
			<div id = "productadd">
				<h6>Add a product:</h6>
				<form action = "/categories/${category.id}/add" method = "post" >
					<select name = "name">
						<c:forEach items = "${menu}" var = "m">
							<option value = "${m.name}">${m.name}</option>
						</c:forEach>
					</select>
					<br><br>
					<input type = "submit" value = "Add">
				</form>
			</div>
		</div>
	</div>
</body>
</html>