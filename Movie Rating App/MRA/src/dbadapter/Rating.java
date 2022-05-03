package dbadapter;




public class Rating {
	private int id;
	private int movieRating;
	private String comment;
	private int uid;
	private int mid;
	
	public Rating(int id, int movieRating, String comment,int uid,int mid) {
		this.id = id;
		this.movieRating = movieRating;
		this.comment = comment;
		this.uid = uid;
		this.mid = mid;
	}
	
	public Rating(int movieRating, String comment,int uid,int mid) {
		this.movieRating = movieRating;
		this.comment = comment;
		this.uid = uid;
		this.mid = mid;
	}
	

	
	public int getId() {
		return id;
	}

	public int getMovieRating() {
		return movieRating;
	}

	public String getComment() {
		return comment;
	}
	
	public int getUid() {
		return uid;
	}
	
	public int getMid() {
		return mid;
	}
	
}
