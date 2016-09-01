<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Bookmarks</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty book}">
			<ul>
				<li>${book.name}</li>
				<li>${book.page}</li>
				<li>${book.quote}</li>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No bookmark found</p>
		</c:otherwise>
	</c:choose>
</body>
</html>