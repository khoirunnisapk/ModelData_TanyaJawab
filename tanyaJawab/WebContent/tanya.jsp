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
		Pertanyaan : <input type="text" name="pertanyaan"> <BR>
		Poin yang diberikan : <input type="text" name="poinDiberikan"> <BR>
		kategori : <input type="text" name="kategori"> <BR>
		tingkat : <input type="text" name="tingkat"> <BR>
		<input type="hidden" name="action" value="submitQuestion">
		<input type="submit" />
	</form>
</body>
</html>