<%@page import="it.gestioneautorelibri.model.Autore"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body background="java.jpg">

	<h1 align="center">Trova il Libro nel database SQL</h1>
	<h1>
		<font color="yellow">
			<%=request.getAttribute("messaggioErrore")!=null ? request.getAttribute("messaggioErrore"):""%>
		</font>
	</h1>
	
	<h2 align="center">Inserisci i dati </h2>
	
	<form action="ExecuteSearchLibroServlet" method="post">
	<p><h2>
		Titolo:
		<input type="text" name="titoloInputForm">
		
		Numero Pagine:
		<input type="text" name="numeroPagineInputForm">
		
		Descrizione:
		<input type="text" name="descrizioneInputForm">
	</h2></p>
	<p><h2>
		Genere:
		<input type="text" name="genereInputForm"> 
		
		Autore:
		
		
		<select name="autoriSelect">
			<option value="0">Selezione la voce</option> 
			<%
				List <Autore> listaFormStampa=(List <Autore>) request.getAttribute("listaAutoreAttributeName"); 
			
				for(Autore temp : listaFormStampa){%>
				
					<option value="<%=temp.getId_Autore()%>"><%=temp.getNome()%> <%=temp.getCognome()%></option> 
			
				<%} %>
				
					
			</select>
		
		
		<input type="hidden" name ="listaAutore" value="<%=listaFormStampa%>" />
		<input type="submit" value="Cerca..">
	</h2></p>
		
	</form>
	

</body>
</html>