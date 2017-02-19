<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body background="java.jpg">

	<h1 align="center">Gestione Autore Libri</h1>
	
	<h2 align="center">cosa vuoi fare? </h2>
<center>
	<form action="SearchAutoreServlet" method="post">	
		<input type="submit" value="GESTIONE AUTORE">	
	</form>
	
	<form action="SearchLibroServlet" method="post">	
		<input type="submit" value="GESTIONE LIBRI">	
	</form>
</center>
</body>

</body>
</html>