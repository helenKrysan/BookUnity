package ua.bookUnity.model;

import java.math.BigDecimal;

public class Book {
	
	private Integer bookID;
	private String bookName;
	private String author;
	private String language;
	private Integer yearIssue;
	private String publishingHouse;
	private String description;
	private Integer numberOfPages;
	private BigDecimal price;
	private String impression;
	private String account_fk;
	private Integer condition_fk;
	private Integer category_fk;
	
	public Book() {}
	
	public Book(Integer bookID,String bookName,String author,String language,Integer yearIssue,String publishingHouse,String description,Integer numberOfPages,BigDecimal price,String impression,String account_fk,Integer condition_fk,Integer category_fk) {
		this.bookID=bookID;
		this.bookName=bookName;
		this.author = author;
		this.language=language;
		this.yearIssue=yearIssue;
		this.publishingHouse = publishingHouse;
		this.description=description;
		this.numberOfPages=numberOfPages;
		this.price=price;
		this.impression=impression;
		this.account_fk=account_fk;
		this.condition_fk=condition_fk;
		this.category_fk=category_fk;
	}
	
	
	public String toString() {
		
		return "Book id "+this.bookID+"\naccount fk "+this.account_fk +"\nprice "+this.price;
	}
	
	public Integer getBookID() {
		return bookID;
	}

	public void setBookID(Integer bookID) {
		this.bookID = bookID;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getYearIssue() {
		return yearIssue;
	}

	public void setYearIssue(Integer yearIssue) {
		this.yearIssue = yearIssue;
	}

	public String getPublishingHouse() {
		return publishingHouse;
	}

	public void setPublishingHouse(String publishingHouse) {
		this.publishingHouse = publishingHouse;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImpression() {
		return impression;
	}

	public void setImpression(String impression) {
		this.impression = impression;
	}

	public String getAccount_fk() {
		return account_fk;
	}

	public void setAccount_fk(String account_fk) {
		this.account_fk = account_fk;
	}

	public Integer getCondition_fk() {
		return condition_fk;
	}

	public void setCondition_fk(Integer condition_fk) {
		this.condition_fk = condition_fk;
	}

	public Integer getCategory_fk() {
		return category_fk;
	}

	public void setCategory_fk(Integer category_fk) {
		this.category_fk = category_fk;
	}

	
}
