package ua.bookUnity.model;

import java.io.File;

public class Foto {

	

	private Integer fotoID;
	private File foto;
	private String fotoName;
	private Integer book_fk;
	
	public Foto() {}
	
	public Foto(Integer fotoID,File foto,String fotoName,Integer book_fk) {
		this.fotoID = fotoID;
		this.foto=foto;
		this.fotoName=fotoName;
		this.book_fk=book_fk;
	}
	
	public String toString() {
		return "Foto: "+this.foto.getName()+"\nBook: "+this.book_fk;
	}
	
	
	public Integer getFotoID() {
		return fotoID;
	}

	public void setFotoID(Integer fotoID) {
		this.fotoID = fotoID;
	}

	public File getFoto() {
		return foto;
	}

	public void setFoto(File foto) {
		this.foto = foto;
	}

	public String getFotoName() {
		return fotoName;
	}

	public void setFotoName(String fotoName) {
		this.fotoName = fotoName;
	}

	public Integer getBook_fk() {
		return book_fk;
	}

	public void setBook_fk(Integer book_fk) {
		this.book_fk = book_fk;
	}
	
	
}
