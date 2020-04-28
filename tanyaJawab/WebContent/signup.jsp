<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<form action="ActionController" method="post">
		Enter username : <input type="text" name="username"> <BR>
		Enter education : <input type="text" name="education"> <BR>
		Enter password : <input type="password" name="password"> <BR>
		<input type="hidden" name="action" value="to_signup">
		<input type="submit" />
	</form>
</body>
</html>