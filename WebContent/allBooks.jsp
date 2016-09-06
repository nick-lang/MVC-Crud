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
<div id="container">
    <div id="header">
    		<div id="title">Library</div>
    		<div id="navbar"><a href="http://www.nicklang.io:8080/Bookmark/">home</a></div>
    </div>
    <div class="clearheader"></div>
	<div id="body">
	<c:forEach items="${books}" var="book">
    		<div>
    		<a href="EditBookData.do?isbn=${book.isbn}">
    			<img src="${book.coverImage}" title="${book.title}"/>
    		</a>
		</div>
	</c:forEach>
	</div>            
</div>
</body>
</html>