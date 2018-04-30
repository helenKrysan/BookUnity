package ua.bookUnity.dao;

import java.util.List;

import ua.bookUnity.model.Genre;

public interface GenreDAO {

	Genre save(String genreName);
	Genre update(Integer genreID,String oldGenreName,String newGenreName);
	Genre getOneByName(String genreName);
	Genre getOneByID(Integer genreID);
	List<Genre> getAll();
	void delete(Integer genreID);
	
	
}
