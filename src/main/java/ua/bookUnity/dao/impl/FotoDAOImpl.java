package ua.bookUnity.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import ua.bookUnity.dao.FotoDAO;
import ua.bookUnity.model.Foto;
import ua.bookUnity.service.ConnectionToDB;
import ua.bookUnity.service.DAOException;

public class FotoDAOImpl implements FotoDAO{

	public Foto save(File foto,Integer book_fk) {
		Foto image = null;
		
		String sql ="INSERT INTO foto (book_fk,foto,foto_name) VALUES (?,?,?) RETURNING foto , foto_id";
			
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, book_fk);
			statement.setBinaryStream(2, new FileInputStream(foto));
			statement.setString(3, foto.getName());
			statement.execute();
			ResultSet result = statement.getResultSet();
			 
			File targetFile = new File("img/"+ foto.getName());
		    OutputStream outStream = new FileOutputStream(targetFile);
			
			if(result.next()) {
				byte[] byteImg = result.getBytes("foto");
			    outStream.write(byteImg);
				image = new Foto(result.getInt("foto_id"), targetFile, foto.getName(),book_fk);
			}
				
			outStream.flush();
			outStream.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	public Foto update(Integer fotoID, File foto,Integer book_fk) {
		Foto image = null;
		FotoDAO fotoDAO = new FotoDAOImpl();	
		
		if(fotoDAO.getOneByID(fotoID)!=null) {
			String sql ="UPDATE foto SET book_fk=?,foto=?,foto_name=? WHERE foto_id=?";
			try(Connection connection = ConnectionToDB.getConnectionToDB();
					PreparedStatement statement = connection.prepareStatement(sql);){
					
					statement.setInt(1, book_fk);
					statement.setBinaryStream(2, new FileInputStream(foto));
					statement.setString(3, foto.getName());
					statement.setInt(4, fotoID);
					if(statement.executeUpdate()==1) {
						image = new Foto(fotoID, foto, foto.getName(),book_fk);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} catch (DAOException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} 
		}
		
		

		return image;
	}


	public Foto getOneByID(Integer fotoID) {
		Foto image = null;
		
		String sql ="SELECT * FROM foto WHERE foto_id=?";
			
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, fotoID);
			ResultSet result = statement.executeQuery();
			OutputStream outStream =null;
		    if(result.next()) {
		    	File targetFile = new File("img/"+ result.getString("foto_name"));
			    outStream = new FileOutputStream(targetFile);
				byte[] byteImg = result.getBytes("foto");
			    outStream.write(byteImg);
				image = new Foto(result.getInt("foto_id"), targetFile,result.getString("foto_name"),result.getInt("book_fk"));
				outStream.flush();
				outStream.close();
			}
		    
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}


	public Foto getOneByName(String imageName) {
		Foto image = null;
		
		String sql ="SELECT * FROM foto WHERE foto_name=?";
			
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setString(1, imageName);
			ResultSet result = statement.executeQuery();
			OutputStream outStream =null;
		    if(result.next()) {
		    	File targetFile = new File("img/"+ result.getString("foto_name"));
			    outStream = new FileOutputStream(targetFile);
				byte[] byteImg = result.getBytes("foto");
			    outStream.write(byteImg);
				image = new Foto(result.getInt("foto_id"), targetFile,result.getString("foto_name"),result.getInt("book_fk"));
				outStream.flush();
				outStream.close();
			}
		    
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}


	public List<Foto> getListBookFotos(Integer book_fk) {
		List<Foto> imageList = new LinkedList<Foto>();
		
		String sql ="SELECT * FROM foto WHERE book_fk=?";
			
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, book_fk);
			ResultSet result = statement.executeQuery();
			
			OutputStream outStream =null;
		    while(result.next()) {
		    	File targetFile = new File("img/"+ result.getString("foto_name"));
			    outStream = new FileOutputStream(targetFile);
				byte[] byteImg = result.getBytes("foto");
			    outStream.write(byteImg);
				Foto image = new Foto(result.getInt("foto_id"), targetFile,result.getString("foto_name"),result.getInt("book_fk"));
				imageList.add(image);
			    outStream.flush();
				outStream.close();
			}
		    

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return imageList;
	}

	
	public void delete(Integer fotoID) {
		FotoDAO fotoDAO = new FotoDAOImpl();	
		
		if(fotoDAO.getOneByID(fotoID)!=null) {
			
			String sql ="DELETE FROM foto WHERE foto_id=?";
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
					
					statement.setInt(1, fotoID);
					statement.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				} catch (DAOException e) {
					e.printStackTrace();
				}  
		}

	}

}
