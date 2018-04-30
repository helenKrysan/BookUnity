package ua.bookUnity.dao;

import java.util.List;

import ua.bookUnity.model.Category;

public interface CategoryDAO {

	Category save(String categoryName);
	Category update(Integer categoryID,String categoryName);
	List<Category> getAll();
	Category getOneByID(Integer categoryID);
	Category getOneByName(String categoryName);
	void delete(Integer categoryID);
	
}
