package ua.bookUnity.model;

public class Genre {



	private Integer genreID;
	private String genreName;
	
	public Genre() {}
	
	public Genre(Integer genreID,String genreName) {
		this.genreID = genreID;
		this.genreName = genreName;
	}
	
	public String toString() {
		return "ID "+this.genreID+"\nName "+this.genreName;
	}
	
	public Integer getGenreID() {
		return genreID;
	}

	public void setGenreID(Integer genreID) {
		this.genreID = genreID;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
}
