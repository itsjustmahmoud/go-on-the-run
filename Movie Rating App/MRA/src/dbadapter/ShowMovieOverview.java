package dbadapter;

import datatypes.MovieData;


public class ShowMovieOverview {
	private int id;
	private MovieData movieData;
	private float movieAvgRating;
	
	public ShowMovieOverview(int id, MovieData movieData, float movieAvgRating) {
		this.id = id;
		this.movieData = movieData;
		this.movieAvgRating = movieAvgRating;
	}
	public ShowMovieOverview(MovieData movieData) {
		this.movieData = movieData;
	}
	public ShowMovieOverview(int id,MovieData movieData) {
		this.id = id;
		this.movieData = movieData;
	}
	
	public ShowMovieOverview( float movieAvgRating,int id) {
		this.id = id;
		this.movieAvgRating = movieAvgRating;
	}
	
	public String toString() {
		return "Movie " + id + " Title: " + movieData.getTitle() + " Director: " + movieData.getDirector() + " Actors Name: " + 
	movieData.getActorsName() + " Original Publishing Data " + movieData.getOrigPubDate() + " Movie Average Rating: " + movieAvgRating ;
	}
	
	public MovieData getMovieData() {
		return movieData;
	}

	public int getId() {
		return id;
	}

	public float getMovieAvgRating() {
		return movieAvgRating;
	}
	
}
