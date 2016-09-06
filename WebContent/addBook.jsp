<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
<title>Add a Book</title>
</head>
<body>
	<form action="AddBookData.do" method="POST">
		ISBN:
		<input type="text" name="isbn"/><br/>
		Title:
		<input type="text" name="title"/><br/>
		Author: 
		<input type="text" name="author"/><br/>
		<input type="submit" value="Add Book" />
	</form>
</body>
</html>