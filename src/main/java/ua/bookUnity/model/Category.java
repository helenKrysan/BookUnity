package ua.bookUnity.model;


public class Category {



	private Integer categoryID;
	private String categoryName;
	
	public Category(){}
	
	public Category(Integer categoryID,String categoryName) {
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}
	
	
	public String toString() {
		return "ID "+this.categoryID+"\nName "+this.categoryName;
	}
	

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
