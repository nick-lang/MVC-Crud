<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
<title>Books</title>
</head>
<body>
<c:forEach items="${books}" var="book">
    <div>
    <img src="${book.coverImage}" alt="${book.title}"/>
	</div>
</c:forEach>
</body>
</html>