<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body background="java.jpg">

	<h1 align="center">Trova il tuo autore nel database SQL</h1>
	<h1>
		<font color="yellow">
			<%=request.getAttribute("messaggioErrore")!=null ? request.getAttribute("messaggioErrore"):""%>
		</font>
	</h1>
	
	<h2 align="center">Inserisci i dati </h2>
	
	<form action="ExecuteSearchAutoreServlet" method="post">
	<p><h2>
		Nome:
		<input type="text" name="nomeInputForm">
		
		Cognome:
		<input type="text" name="cognomeInputForm">
		
		Eta':
		<input type="text" name="etaInputForm">
	</h2></p>
	<p><h2>
		Casa Editrice:
		<input type="text" name="casaEditriceInputForm"> 
		 
		
		PartitaIva. :
		<input type="text" name="partitaIvaInputForm"> 
		
		
		<input type="submit" value="Cerca..">
	</h2></p>
		
	</form>
	

</body>
</html>