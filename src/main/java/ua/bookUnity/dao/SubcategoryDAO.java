 package ua.bookUnity.dao;

import java.util.List;

import ua.bookUnity.model.Subcategory;

public interface SubcategoryDAO {

	Subcategory save(String subcategoryName, String description, Integer genre_fk);
	Subcategory update(Integer subcategoryID,String subcategoryName, String description, Integer genre_fk);
	List<Subcategory> getAll();
	List<Subcategory> getAllSubcOfGenre(Integer genre_fk);
	List<String> getAllNameSubcOfGenre(Integer genre_fk);
	Subcategory getOneById(Integer subcategoryID);
	Subcategory getOneByName(String subcategoryName);
	void delete(Integer subcategoryID);
	
}
