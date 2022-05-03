<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="css/mystyle.css">
	<script>
		function getVal(value)
		  {
		    document.getElementById('myForm').action = "showMovies?action=addRating&id="+value;
		  }
	</script>
</head>
<body>

<#include "header.ftl">

<div class="error">${errormessage}</div>

<#include "footer.ftl">
</body>