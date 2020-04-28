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
		<form action="ActionController" method="post">
			<input type="submit" name="action" value="Retrieve">
			<input type="submit" name="action" value="Tanya">
			<input type="submit" name="action" value="Searching">
        </form>
        <table border="1" cellpadding="5">
            <caption><h2>List of Questions</h2></caption>
            <tr>
                <th>Username</th>
                <th>Pertanyaan</th>
                <th>Kategori</th>
                <th>Tingkat</th>
                <th>Poin</th>
                <th>Status</th>
                <th>Jawab</th>
            </tr>
            <c:forEach items="${dataList}" var="dataItem">
            	<form action="ActionController" method="post">
			        <tr>
			            <td>${dataItem.uname}</td>
			            <td>${dataItem.pertanyaan}</td>
			            <td>${dataItem.kategori}</td>
			            <td>${dataItem.tingkat}</td>
			            <td>${dataItem.poinDiberikan}</td>
			            <td>${dataItem.status}</td>
			            <td><input type="submit" name="action" value="jawab"></td>
			        </tr>
					<input type="hidden" name="kode" value="${dataItem.getKode()}">
			        <input type="hidden" name="status" value="${dataItem.status}">
			        <input type="hidden" name="pertanyaan" value="${dataItem.pertanyaan}">
			        
			     </form>
		    </c:forEach>
        </table>
    </div>
</body>
</html>