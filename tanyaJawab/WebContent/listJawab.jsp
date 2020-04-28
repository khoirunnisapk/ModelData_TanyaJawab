<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Answers of This Question</h2></caption>
            <tr>
                <th>Username</th>
                <th>Jawaban</th>
                <th>Vote</th>
            </tr>
            <c:forEach items="${dataList}" var="dataItem">
            	<form action="ActionController" method="post">
			        <tr>
			            <td>${dataItem.uname}</td>
			            <td>${dataItem.getJawaban()}</td>
			            <td><input type="submit" name="action" value="dukung"></td>
			        </tr>
			     </form>
		    </c:forEach>
        </table>
    </div>
</body>
</html>