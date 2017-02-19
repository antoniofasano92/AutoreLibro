<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script>

function scriptDiControllo(){
	var username=document.getElementById("usernameControl").value;
	var password=document.getElementById("passwordControl").value;

	
	if(username==""){
		alert("Non hai inserito la tua Username");
		return false;
		
	}
	if(password==""){
		alert("Non hai inserito la tua Password");
		return false;
		
	}
}


</script>

</head>
<body background="java.jpg">

	<h1 align="center">Benvenuto nella Gestione Autori & Libri</h1>
	
	<h2 align="center">Inserisci le tue credenziali per accedere </h2>
	
<center>
		<h3>Username:</h3>
		<p>
		<input type="text" id="usernameControl" name="usernameInputCreateForm">
		</p>
		<p>
		<h3>Password:</h3>
		</p>
		<p>
		<input type="text" id="passwordControl" name="passwordInputCreateForm">
		</p>
		<p>
	
	<form action="AccessoGestioneServlet" method="post">	
		<input type="submit" value="ACCEDI" onClick="return scriptDiControllo();" />
	</form>
	
	<h3>
		<font color="red"> 
			<%=request.getAttribute("messaggioErrore") != null ? request.getAttribute("messaggioErrore") : ""%>
		</font>
	</h3>
	
</center>
</body>
</html>