<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="css/mystyle.css">
	<#assign x=mid>
</head>
<body>
<#include "header.ftl">
<b>Welcome to our little demonstration on the MRA Webapp</b><br><br>

<#if condition == "noCommentNoRating">
    	<form method="POST" action="showMovies?action=insertRating&id=${x}">
	    	<div class="myInput">
				<label  class="left">Rating </label>
				<input  class="right" type="number" min=1 max=10 name="rating" onKeyDown="return false" value=1>
				
			</div>
			<div class="myInput">
				<label  class="left">Comment</label>
				<input  class="right" type="text" name="comment">
			</div>
			<button  id="addRating" type="submit" >Add Rating</button>
		</form>	
	<#elseif condition == "commentAndRating">
		<div>You already rated and commented this movie!</div>
	<#else>
	<form method="POST" action="showMovies?action=insertComment&id=${x}">
    	<div>You already rated this movie, you can add now a comment</div>
			<div class="myInput">
				<label  class="left">Comment</label>
				<input  class="right" type="text" name="comment">
			</div>
		<button  id="addRating" type="submit" >Add Rating</button>
	</form>
		
	</#if>

</body>
</html>