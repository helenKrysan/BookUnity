package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Book;
import java.io.File;
import io.swagger.model.ModelApiResponse;
import ua.bookUnity.dao.BookDAO;
import ua.bookUnity.dao.CategoryDAO;
import ua.bookUnity.dao.BookGenreDAO;
import ua.bookUnity.dao.ConditionDAO;
import ua.bookUnity.dao.GenreDAO;
import ua.bookUnity.dao.SubcategoryDAO;
import ua.bookUnity.dao.impl.BookDAOImpl;
import ua.bookUnity.dao.impl.CategoryDAOImpl;
import ua.bookUnity.dao.impl.ConditionDAOImpl;
import ua.bookUnity.dao.impl.GenreDAOImpl;
import ua.bookUnity.dao.impl.SubcategoryDAOImpl;
import ua.bookUnity.dao.impl.BookGenreDAOImpl;
import ua.bookUnity.model.Category;

import java.util.LinkedList;
import java.util.List;
import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-04-24T18:42:49.417Z")
public class BookApiServiceImpl extends BookApiService {
    
	@Override
    public Response addBook(Book body, SecurityContext securityContext) throws NotFoundException {
    	CategoryDAO categoryDAO = new CategoryDAOImpl();
    	Category category = categoryDAO.getOneByName(body.getCategory());
    	ConditionDAO conditionDAO = new ConditionDAOImpl();
    	ua.bookUnity.model.Condition condition = conditionDAO.getOneByName(body.getCondition());
    	BookDAO bookDAO = new BookDAOImpl();
    	ua.bookUnity.model.Book book = bookDAO.save(body.getName(), body.getAuthor(), body.getLanguage(), body.getYearOfIssue(), body.getPublishingHouse(), body.getDescription(), body.getNumberOfPages(), body.getPrice(), body.getOwnImpression(),body.getLogin() , condition.getConditionID(),  category.getCategoryID());
    	
    	BookGenreDAO bookGenreDAO = new BookGenreDAOImpl();
    	SubcategoryDAO subcatDAO = new SubcategoryDAOImpl();
    	GenreDAO genreDAO = new GenreDAOImpl();
    	
    	for(Genre gen: body.getGenre()) {	
    		ua.bookUnity.model.Subcategory subcat = subcatDAO.getOneByName(gen.getName());
    		ua.bookUnity.model.Genre genre = genreDAO.getOneByName(gen.getName());
    		bookGenreDAO.save(book.getBookID(), genre.getGenreID(),subcat.getSubcategoryID());
    	}
    	
    	if(book!=null) {
       	 return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Book is created!!!")).build();
       }
       return Response.status(Status.CONFLICT).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Book can't be created!!!")).build();
    }
    @Override
    public Response deleteBook(Long bookId, String apiKey, SecurityContext securityContext) throws NotFoundException {
        
    	BookDAO bookDAO = new BookDAOImpl();
    	bookDAO.delete(bookId.intValue());  	
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Book is deleted!!!")).build();
    }
    @Override
    public Response findBook( @NotNull String category,  List<String> genre,  String author,  Integer year, SecurityContext securityContext) throws NotFoundException {
    	BookDAO bookDAO = new BookDAOImpl();
    	
    	List<ua.bookUnity.model.Book> bookList = new LinkedList<>();
    	List<Book> resultList = new LinkedList<>();
    	
    	if(category!=null) {
    		CategoryDAO categoryDAO = new CategoryDAOImpl();
        	Category categ = categoryDAO.getOneByName(category);
        	if(categ!=null) {
        		List<ua.bookUnity.model.Book> list = bookDAO.getAllByCategory(categ.getCategoryID());
        		bookList.addAll(list);
        	}   		    		
    	}
    	
    	
    	
    	if(author!=null) {
        	List<ua.bookUnity.model.Book> list = bookDAO.getAllByAuthor(author);
           	bookList.addAll(list);	  		    		
    	}
    	
    	if(year!=null) {
        	List<ua.bookUnity.model.Book> list = bookDAO.getAllByYear(year);
        	bookList.addAll(list);	  		    		
    	}
    	
    	for(String gen: genre) {
    		List<ua.bookUnity.model.Book> list = bookDAO.getAllByGenre(gen);
        	bookList.addAll(list);
    	}
    	
    	for(ua.bookUnity.model.Book book : bookList) {
    		Book normalBook = normalizeBook(book);
    		if(!resultList.contains(normalBook)) {
    			resultList.add(normalBook);
    		}
    	}
    	List<Book> resultListEx = new LinkedList<>();
    	resultListEx.add(exampleAdd());
        return Response.ok(resultListEx,MediaType.APPLICATION_JSON).build();
    }
    @Override
    public Response getBookById(Long bookId, SecurityContext securityContext) throws NotFoundException {
    	BookDAO bookDAO = new BookDAOImpl();
    	ua.bookUnity.model.Book book = bookDAO.getOneByID(bookId.intValue());
    	if(book!=null) {
    		return Response.ok(normalizeBook(book),MediaType.APPLICATION_JSON).build();
    	}
    	
    	return Response.status(Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "No book with this ID!!!")).build();
    }
    @Override
    public Response updateBook(Book body, SecurityContext securityContext) throws NotFoundException {
        
    	CategoryDAO categoryDAO = new CategoryDAOImpl();
    	Category category = categoryDAO.getOneByName(body.getCategory());
    	ConditionDAO conditionDAO = new ConditionDAOImpl();
    	ua.bookUnity.model.Condition condition = conditionDAO.getOneByName(body.getCondition());
    	BookDAO bookDAO = new BookDAOImpl();
    	ua.bookUnity.model.Book book = bookDAO.update(body.getId().intValue(),body.getName(), body.getAuthor(), body.getLanguage(), body.getYearOfIssue(), body.getPublishingHouse(), body.getDescription(), body.getNumberOfPages(), body.getPrice(), body.getOwnImpression(),body.getLogin() , condition.getConditionID(),  category.getCategoryID());
       
    	if(book!=null) {
       	 return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Book is updated!!!")).build();
       }
    	
    	 return Response.status(Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Book can't be updated!!!")).build();
    }
    @Override
    public Response uploadFile(Long bookId, String additionalMetadata, InputStream fileInputStream, FormDataContentDisposition fileDetail, SecurityContext securityContext) throws NotFoundException {
        
    	//write upload
    	
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response showBooks(String login, SecurityContext securityContext) throws NotFoundException {
    	BookDAO bookDAO = new BookDAOImpl();
    	
    	List<ua.bookUnity.model.Book> bookList = new LinkedList<>();
    	List<Book> resultList = new LinkedList<>();

     	if(login!=null) {
        	List<ua.bookUnity.model.Book> list = bookDAO.getAllByAccount(login);
        	bookList.addAll(list);	  		    		
    	}
     	
       	for(ua.bookUnity.model.Book book : bookList) {
    		Book normalBook = normalizeBook(book);
    		if(!resultList.contains(normalBook)) {
    			resultList.add(normalBook);
    		}
    	}
     	
     	return Response.ok(resultList,MediaType.APPLICATION_JSON).build();
    }
    private Book exampleAdd() {
    	Book result = new Book();
    	result.setAuthor("Author1");
    	result.setName("Book1");
    	result.setLanguage("Language1");
    	result.setYearOfIssue(1999);
    	result.setPublishingHouse("house1");
    	result.setDescription("fun book");
    	result.setNumberOfPages(78);
    	result.setPrice("$12");
    	result.setOwnImpression("Cool book");
    	return result;
    }
    
    private Book normalizeBook(ua.bookUnity.model.Book book) {
    	Book result = new Book();
    	result.setId(book.getBookID().longValue());
    	result.setAuthor(book.getAuthor());
    	result.setName(book.getBookName());
    	result.setLanguage(book.getLanguage());
    	result.setYearOfIssue(book.getYearIssue());
    	result.setPublishingHouse(book.getPublishingHouse());
    	result.setDescription(book.getDescription());
    	result.setNumberOfPages(book.getNumberOfPages());
    	result.setPrice(book.getPrice().toString());
    	result.setOwnImpression(book.getImpression());
    	result.setLogin(book.getAccount_fk());
    	
    	CategoryDAO categoryDAO = new CategoryDAOImpl();
    	Category category = categoryDAO.getOneByID(book.getCategory_fk());
    	
    	result.setCategory(category.getCategoryName());
    	
    	ConditionDAO conditionDAO = new ConditionDAOImpl();
    	ua.bookUnity.model.Condition condition = conditionDAO.getOneByID(book.getCondition_fk());
    	
    	result.setCondition(condition.getConditionName());
    	
    	
    	return result;
    }
}
