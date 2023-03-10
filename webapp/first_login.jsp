<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>This is First Login Page</title>
	<link href = "styles/main.css" rel = "stylesheet" type = "text/css">
</head>
<body>
<%
String err = (String) request.getAttribute("err");
if(err == null) err = "";
%>

<div class = "main_first_login">
	<h1>This is Login first time page</h1>
	<form action="/Assignment6/FirstLoginController" method = "post">
		<p>Please answer at least one (1) of the hind question bellow. Maximum selection is three (3) questions</p>
		<div class = "session">
			<select>
				<option selected="selected"> Select the first question </option>
				<option> Question 1 </option>
				<option> Question 2 </option>
				<option> Question 3 </option>
			</select>
			<input type = "text" name = "ans1"/>
		</div>
		
		<div class = "session">
			<select>
				<option selected = "selected"> Select the second question </option>
				<option> Question 1 </option>
				<option> Question 2 </option>
				<option> Question 3 </option>
			</select>
			<input type = "text" name = "ans2"/>
		</div>
		
	
		<div class = "session">
			<select>
				<option selected = "selected"> select the thirst question </option>
				<option> Question 1 </option>
				<option> Question 2 </option>
				<option> Question 3 </option>
			</select>
			<input type = "text" name = "ans3"/>
		</div>
		<p>Please key in your old and new password. The new password must be different from the old password.</p>
		<div class = "main">
			<div class = "first_main">
				<label for = "oldPas">Old Password</label>
				<input type = "password" name = "oldPas" id = "oldPas">
			</div>
			
			<div class = "first_main">
				<label for = "newPas">New Password</label>
				<input type = "password" name = "newPas" id = "newPas">
			</div>
			
			<div class = "first_main">
				<label for = "conPas">Confirm New Password</label>
				<input type = "password" name = "conPas" id = "conPas">
			</div>
		</div>
		<p class = "err"><%=err %></p>
		<div class = "act_btn">
			<input type = "submit" value = "Submit" name = "send"/>
			<input type = "submit" value = "Cancel" name = "send"/>
			<input type = "submit" value = "Clear" name = "send"/>
		</div>
	</form>
</div>
</body>
</html>