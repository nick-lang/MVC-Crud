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
<div id="container">
    <div id="header">
    		<div id="title">Library</div>
    		<div id="navbar"><a href="www.nicklang.io:8080/Bookmark">home</a></div>
    </div>
    <div class="clearheader"></div>
    <div class="edit-add">
	<form action="AddBookData.do" method="POST">
		ISBN:<br/>
		<input type="text" name="isbn"/><br/>
		Title:<br/>
		<input type="text" name="title"/><br/>
		Author:<br/>
		<input type="text" name="author"/><br/>
		<input type="submit" value="Add Book" />
	</form>
	</div>
</div>
</body>
</html>