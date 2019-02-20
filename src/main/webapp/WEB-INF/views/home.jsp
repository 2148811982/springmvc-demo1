<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spittr</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/style.css'/>">
</head>
<body>
	<h1>Welcome to Spittr!</h1>
	<a href="<c:url value='/spittles'/>">Spittles</a>
	<a href="<c:url value='/spitter/register'/>">Register</a>
</body>
</html>