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

<b>Welcome to our little demonstration on the MRA Webapp</b><br><br>

<form method="POST" action="showMovies?action=insertMovie">
	<fieldset>
		<legend>Required Informationnn</legend>
		<div class="myInput">
			<label class="left">Title</label>
			<input type="text" name="title" class="right">
	    </div>

		<div class="myInput">
			<label class="left">Director</label>
			<input type="text" name="director" class="right">
	    </div>

		<div class="myInput">
			<label class="left">Actors Names</label>
			<input type="text" name="actorsName"  class="right">
	    </div>
	    
	    <div class="myInput">
			<label class="left">Original Publishing Date</label>
			<input type="text" name="origPubDate" class="right">
	    </div>

		
	</fieldset>
	<button type="submit" id="submit">Submit</button>
</form>
<#include "footer.ftl">
</body>