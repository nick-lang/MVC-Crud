<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Edit Book</title>
</head>
<body>
	<div>
		<img src="${book.coverImage}" title="${book.title}"/><br/>
		Title: ${book.title}<br/>
		Author: ${book.author}<br/>
		Delete Book
	</div>
	<div>
	<form action="AddBookData.do" method="POST">
		Title:
		<input type="text" name="title"/><br/>
		Author: 
		<input type="text" name="author"/><br/>
		<input type="submit" value="Edit Book" />
	</form>
	</div>
</body>
</html>