package ua.bookUnity.dao;

import java.io.File;
import java.util.List;

import ua.bookUnity.model.Foto;

public interface FotoDAO {

	Foto save (File foto,Integer book_fk);
	Foto update(Integer fotoID,File foto,Integer book_fk);
	Foto getOneByID(Integer fotoID);
	Foto getOneByName(String imageName);
	List<Foto> getListBookFotos(Integer book_fk);
	void delete(Integer fotoID);
}
