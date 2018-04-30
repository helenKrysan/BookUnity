package ua.bookUnity.model;


public class WishList {

	private String login_fk;
	private Integer book_fk;
	
	public WishList(){}
	
	public WishList(String login_fk,Integer book_fk) {
		this.login_fk=login_fk;
		this.book_fk=book_fk;
	}
	
	public String toString() {
		return "Login: "+this.login_fk+"\nBook: "+this.book_fk+"\n";
	}
	
	public String getLogin_fk() {
		return login_fk;
	}

	public void setLogin_fk(String login_fk) {
		this.login_fk = login_fk;
	}

	public Integer getBook_fk() {
		return book_fk;
	}

	public void setBook_fk(Integer book_fk) {
		this.book_fk = book_fk;
	}
	
}
