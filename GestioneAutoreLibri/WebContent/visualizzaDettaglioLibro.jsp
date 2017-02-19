<%@page import="it.gestioneautorelibri.model.Autore"%>
<%@page import="it.gestioneautorelibri.model.Libro"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body background="java.jpg">
<center>

<h1 align="center">Dettagli Del Libro</h1>
	<% 
	Libro libroStampa= (Libro) request.getAttribute("idLibroAttributeName");%>

<table border="5" align="center">	
	<thead>
			<tr>
				<td><h2>ID</h2></td>
				<td><h2>Titolo</h2></td>
				<td><h2>Numero Pagine</h2></td>
				<td><h2>Descrizione</h2></td>
				<td><h2>Genere</h2></td>

				<td><h2>Azione</h2></td>
				
			</tr>
			<tr>
				<td><h2><%=libroStampa.getId_Libro()%></h2></td>
				<td><h2><%=libroStampa.getTitolo()%></h2></td>
				<td><h2><%=libroStampa.getnPagine()%></h2></td>
				<td><h2><%=libroStampa.getDescrizione()%></h2></td>
				<td><h2><%=libroStampa.getGenere()%></h2></td>
				<td><h2><a href="ExecuteDeleteLibroServlet?idLibroDaCencellareDalDatabase=<%=libroStampa.getId_Libro()%>" onClick="return confirm('Sei Sicuro di Voler cancellare?');">Cancella Il Libro</a></h2></td>
			</tr>
			
		</thead>
	</table>
	
	
<h1><strong>Autore del Libro</strong></h1>	
	
	
<table border="5" align="center">		
	<thead>
			<tr>
				<td><h2>ID</h2></td>
				<td><h2>Nome</h2></td>
				<td><h2>Cognome</h2></td>
				<td><h2>Eta'</h2></td>
				<td><h2>Casa Editrice</h2></td>
				<td><h2>PartitaIva</h2></td>
			</tr>
			<%
			Autore autore=new Autore();
			autore=libroStampa.getAutore(); %>
			<tr>
				<td><h2><%=autore.getId_Autore()%></h2></td>
				<td><h2><%=autore.getNome()%></h2></td>
				<td><h2><%=autore.getCognome()%></h2></td>
				<td><h2><%=autore.getEta()%></h2></td>
				<td><h2><%=autore.getCasaEditrice()%></h2></td>
				<td><h2><%=autore.getPartitaIva()%></h2></td>
			</tr>
			
		</thead>
	</table>
	
<h2><a href="homePage.jsp">Torna indietro</a></h2>
	
</center>	
</body>
</html>