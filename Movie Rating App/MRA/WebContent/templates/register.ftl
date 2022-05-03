<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="css/mystyle.css">
	
</head>
<body>
<#include "header.ftl">
<b>Welcome to our little demonstration on the MRA Webapp, Please Register</b><br><br>

<form method="POST" action="register?action=addUser">
	<fieldset id="insertoffer">
		<legend>Required Informationnn</legend>
		<div class="myInput">
			<label class="left">Username</label>
			<input type="text" name="username" id="username" class="right">
	    </div>

		<div class="myInput">
			<label class="left">Email</label>
			<input type="text" name="email" id="email" class="right">
	    </div>

		<div class="myInput">
			<label class="left">Age</label>
			<input type="number" name="age" class="right">
	    </div>
	
	</fieldset>
	<button type="submit" id="submit" >Submit</button>
</form>
</body>
