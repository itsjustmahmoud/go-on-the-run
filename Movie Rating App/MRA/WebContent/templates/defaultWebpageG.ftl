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
			<form method="POST" id="addMovieButton" action="showMovies?action=addingMovie">
       <button href="showMovies?action=addingMovie" type="submit">Add New Movie</button>
     </form>
	<div>
	<form method="POST" id="myForm" >
		<table id="showAllMovies">
			<tr>
				<th>Title</th>
				<th>Director</th>
				<th>Actors Names</th>
				<th>Original Publishing Date</th>
				<th>Movie Average Rating</th>
				<th>Add Rating</th>
			</tr>
			<#list allMovies as m>
			<tr>
				<td>${m.movieData.title}</td>
				<td>${m.movieData.director}</td>
				<td>${m.movieData.actorsName}</td>
				<td>${m.movieData.origPubDate}</td>
				
				<#if m.movieAvgRating == 0>
	        		<td>No Ratings yet</td>
	   			<#else>
        			<td>${m.movieAvgRating}</td>
    			</#if>
				
				
				<td><button class='buttonClass' type="submit" name=${m.id} onClick={getVal(${m.id})} value='Add Rating'>Add Rating</button></td>
			</tr>
			</#list>
		</table>
	</form>
		
		
	</div>

</body>
</html>