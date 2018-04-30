package ua.bookUnity.model;

public class Subcategory {

	

	private Integer subcategoryID;
	private String subcategoryName;
	private String description;
	private Integer genre_fk;
	
	public Subcategory() {}
	
	public Subcategory(Integer subcategoryID,String subcategoryName, String description, Integer genre_fk) {
		this.subcategoryID=subcategoryID;
		this.subcategoryName=subcategoryName;
		this.description=description;
		this.genre_fk=genre_fk;
	}
	
	public String toString() {
		return "Name: "+this.subcategoryName+"\nGenre fk: "+this.genre_fk;
	}
	public Integer getSubcategoryID() {
		return subcategoryID;
	}

	public void setSubcategoryID(Integer subcategoryID) {
		this.subcategoryID = subcategoryID;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getGenre_fk() {
		return genre_fk;
	}

	public void setGenre_fk(Integer genre_fk) {
		this.genre_fk = genre_fk;
	}
}
