<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
<title>Edit Book</title>
</head>
<body>
	<div>
		<img src="${book.coverImage}" title="${book.title}"/><br/>
		Title: ${book.title}<br/>
		Author: ${book.author}<br/>
		<form action="DeleteBookData.do" method="POST">
			<input type="hidden" value="${book.isbn}" name="isbn"/> 
			<input type="submit" value="Delete Book" />
		</form>
	</div>
	<div>
	<form action="EditBookData.do" method="POST">
		Title:
		<input type="text" name="title"/><br/>
		Author: 
		<input type="text" name="author"/><br/>
		<input type="hidden" value="${book.isbn}" name="isbn"/> 
		<input type="submit" value="Edit Book" />
	</form>
	</div>
</body>
</html>