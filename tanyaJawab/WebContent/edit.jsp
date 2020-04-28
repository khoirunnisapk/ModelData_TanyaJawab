<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>     
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<form action="ActionController" method="post">
		Update name : <input type="text" name="name" value="${student.vname}"> <BR> 
		Update city : <input type="text" name="city" value="${student.vcity}"> <BR> 
		Update designation : <input type="text" name="designation" value="${student.vdesignation}"> <BR>
		Update salary : <input type="text" name="salary" value="${student.vsalary}"> <BR>
		<input type="hidden" name="row" value="${student.vRow}">
		<input type="hidden" name="action" value="to_update">
		<input type="submit" />
		</form>	
</body>
</html>