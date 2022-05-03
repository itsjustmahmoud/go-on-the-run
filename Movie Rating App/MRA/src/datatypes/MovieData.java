package datatypes;

public class MovieData {
	private String title;
	private String director;
	private String actorsName;
	private int origPubDate;

	public MovieData(String title, String director, String actorsName, int origPubDate) {
		this.title = title;
		this.director = director;
		this.actorsName = actorsName;
		this.origPubDate = origPubDate;
		
	}

	public String getTitle() {
		return title;
	}

	public String getDirector() {
		return director;
	}
	
	public String getActorsName() {
		return actorsName;
	}
	
	public int getOrigPubDate() {
		return origPubDate;
	}
}
