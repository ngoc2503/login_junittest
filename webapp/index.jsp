<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>This is Login page</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
</head>
<body>


<%
String err = (String) request.getAttribute("err");
if(err == null) err = "";
%>
<div class ="main">
	<h1>Login System</h1>	
	<form action="/Assignment6/LoginController" method = "post">
		<div class = "wb_form">
			<label for="userId">
				UserID
			</label>
			<input id = "userId" type = "number" name = "userId"/>
		</div>
		
		<div class = "wb_form">
			<label for="userPassword">
			Password
			</label>
			<input id = "userPassword" type = "password" name = "password"/>
		</div>
		<p class = "err"><%=err %></p>
		<div class = "wb_form">
			<input type = "submit" value = "Submit" name ="login"/>
			<input type = "submit" value = "Clear" name = "login"/>
		</div>
			
	</form>

</div>

</body>
</html>