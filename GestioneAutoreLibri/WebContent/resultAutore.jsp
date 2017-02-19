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
<h1 align="center">Elenco Degli Autori</h1>
<table border="5" align="center">
		<thead>
			<tr>
				<td>Nome Autore</td>
				<td>Cognome Autore</td>
				<td>Azione</td>
			</tr>
		</thead>
			<%
			List <Autore> listaFormStampa=(List <Autore>) request.getAttribute("listaAutoreAttributeName"); 
			for(Autore temp : listaFormStampa){%>
			<tr>
				<td><%=temp.getNome()%></td>
				<td><%=temp.getCognome()%></td>
				<td><a href="DettagliAutoreServlet?idDaInviareComeParametro=<%=temp.getId_Autore()%>">Dettaglio</a><a href="ModifyAutoreServlet?idDaInviareComeParametro=<%=temp.getId_Autore()%>">Modifica</a></td>
			
			</tr>
			<%} %>
			<td>
			<form action="CreateAutoreServlet" method="post">
				<input type="submit" value="Inserisci Nuovo Autore">
			</form>
	</td>
		</thead>
	</table>
	<h2><a href="ricercaAutori.jsp">Torna indietro</a></h2>

	
</body>

</html>