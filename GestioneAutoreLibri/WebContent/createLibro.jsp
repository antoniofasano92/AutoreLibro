<%@page import="it.gestioneautorelibri.model.Autore"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script>

function scriptDiControllo(){
	var titoloLibro=document.getElementById("titoloControl").value;
	var numeroPagineLibro=document.getElementById("numeroPagineControl").value;
	var descrizioneLibro=document.getElementById("descrizioneControl").value;
	var genereLibro=document.getElementById("genereControl").value;
	var autoriSelectLibro=document.getElementById("autoriSelectControl").value;
	
	if(titoloLibro==""){
		alert("Non hai inserito il Titolo");
		return false;
		
	}
	if(numeroPagineLibro<="0"){
		alert("Non hai inserito il Numero delle Pagine");
		return false;
		
	}
	if(numeroPagineLibro==""){
		alert("Non hai inserito il Numero delle Pagine");
		return false;
		
	}
	if(descrizioneLibro==""){
		alert("Non hai inserito la Descrizione");
		return false;
		
	}
	if(genereLibro==""){
		alert("Non hai inserito il Genere");
		return false;
		
	}
	if(autoriSelectLibro=="0"){
		alert("Non hai inserito l'Autore");
		return false;
		
	}
}


</script>
</head>
<body background="java.jpg">

	<h1 align="center">Aggiungi un nuovo Libro nel database SQL</h1>

	
	<h2 align="center">Inserisci i dati </h2>
	
	<form action="ExecuteCreateLibroServlet" method="post">
	<h2>
		Titolo:
		<p>
		<input type="text" id="titoloControl" name="titoloInputForm">
		</p>
		<p>
		Numero Pagine:
		</p>
		<p>
		<input type="text" id="numeroPagineControl" name="numeroPagineInputForm">
		</p>
		<p>
		Descrizione:
		</p>
		<p>
		<input type="text" id="descrizioneControl" name="descrizioneInputForm">
		</p>
		<p>
		Genere:
		</p>
		<p>
		<input type="text" id="genereControl" name="genereInputForm"> 
		</p>
		<p>
		Autore:
		</p>
		<select name="autoriSelect"  id="autoriSelectControl">
			<option value="0">Selezione la voce</option> 
			<%
				List <Autore> listaFormStampa=(List <Autore>) request.getAttribute("listaAutoreAttributeName"); 
			
				for(Autore temp : listaFormStampa){%>
				
					<option value="<%=temp.getId_Autore()%>"><%=temp.getNome()%> <%=temp.getCognome()%></option> 
			
				<%} %>
				
					
			</select>
		
		<input type="hidden" name ="listaAutore" value="<%=listaFormStampa%>" />
		<input type="submit" value="Aggiungi il libro" onClick="return scriptDiControllo();" />
	</h2>
		
	</form>
	<a href="homePage.jsp">Torna indietro</a>
	

</body>
</html>