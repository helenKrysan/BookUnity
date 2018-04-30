package ua.bookUnity.dao;

import java.util.List;
import java.util.Map;

import ua.bookUnity.model.Book;
import ua.bookUnity.model.BookGenre;
import ua.bookUnity.model.Genre;
import ua.bookUnity.model.Subcategory;

public interface BookGenreDAO {

	BookGenre getByAllFields(Integer book_fk,Integer genre_fk,Integer subcategory_fk);
	BookGenre save(Integer book_fk,Integer genre_fk,Integer subcategory_fk);
	List<Book> getBookByGenre(Integer genre_fk);
	List<Book> getBookBySubcategory(Integer subcategory_fk);
	Map<Subcategory,Genre> getBookGenres(Integer book_fk);
	void delete(Integer book_fk,Integer genre_fk,Integer subcategory_fk);
	
}
