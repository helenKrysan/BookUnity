package ua.bookUnity.model;

public class BookGenre {
	



	private Integer book_fk;
	private Integer genre_fk;
	private Integer subcategory_fk;
	
	public BookGenre() {}
	
	public BookGenre(Integer book_fk,Integer genre_fk,Integer subcategory_fk) {
		this.book_fk=book_fk;
		this.genre_fk=genre_fk;
		this.subcategory_fk=subcategory_fk;
	}
	
	
	public String toString() {
		return "Book "+this.book_fk+"\nGenre: "+this.genre_fk+"\nSubCat: "+this.subcategory_fk+"\n";
	}
	
	public Integer getBook_fk() {
		return book_fk;
	}

	public void setBook_fk(Integer book_fk) {
		this.book_fk = book_fk;
	}

	public Integer getGenre_fk() {
		return genre_fk;
	}

	public void setGenre_fk(Integer genre_fk) {
		this.genre_fk = genre_fk;
	}

	public Integer getSubcategory_fk() {
		return subcategory_fk;
	}

	public void setSubcategory_fk(Integer subcategory_fk) {
		this.subcategory_fk = subcategory_fk;
	}
}
