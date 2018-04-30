package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.BookApiService;
import io.swagger.api.factories.BookApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Book;
import java.io.File;
import io.swagger.model.ModelApiResponse;

import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/book")


@io.swagger.annotations.Api(description = "the book API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-04-24T18:42:49.417Z")
public class BookApi  {
   private final BookApiService delegate;

   public BookApi(@Context ServletConfig servletContext) {
      BookApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("BookApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (BookApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = BookApiServiceFactory.getBookApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add a new book to the store", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bookUnity_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:books", description = "read your books"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:books", description = "modify books in your account")
        })
    }, tags={ "book", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 405, message = "Invalid input", response = Void.class) })
    public Response addBook(@ApiParam(value = "Book object that needs to be added to the account" ,required=true) Book body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.addBook(body,securityContext);
    }
    @DELETE
    @Path("/{bookId}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Deletes a book", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bookUnity_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:books", description = "read your books"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:books", description = "modify books in your account")
        })
    }, tags={ "book", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Book not found", response = Void.class) })
    public Response deleteBook(@ApiParam(value = "Book id to delete",required=true) @PathParam("bookId") Long bookId
,@ApiParam(value = "" )@HeaderParam("api_key") String apiKey
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteBook(bookId,apiKey,securityContext);
    }
    @GET
    @Path("/find")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds books", notes = "", response = Book.class, responseContainer = "List", tags={ "book", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Book.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid status value", response = Void.class) })
    public Response findBook(@ApiParam(value = "",required=true, allowableValues="sale, exchange") @QueryParam("category") String category
,@ApiParam(value = "Status values that need to be considered for filter") @QueryParam("genre") List<String> genre
,@ApiParam(value = "") @QueryParam("author") String author
,@ApiParam(value = "") @QueryParam("year") Integer year
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findBook(category,genre,author,year,securityContext);
    }
    @GET
    @Path("/{bookId}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Find book by ID", notes = "Returns a single book", response = Book.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "api_key")
    }, tags={ "book", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Book.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Book not found", response = Void.class) })
    public Response getBookById(@ApiParam(value = "ID of pet to return",required=true) @PathParam("bookId") Long bookId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getBookById(bookId,securityContext);
    }
    @PUT
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update an existing book", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bookUnity_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:books", description = "read your books"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:books", description = "modify books in your account")
        })
    }, tags={ "book", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Book not found", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Validation exception", response = Void.class) })
    public Response updateBook(@ApiParam(value = "Book object that needs to be added to the account" ,required=true) Book body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateBook(body,securityContext);
    }
    @POST
    @Path("/{bookId}/uploadImage")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "uploads an image", notes = "", response = ModelApiResponse.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bookUnity_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:books", description = "read your books"),
            @io.swagger.annotations.AuthorizationScope(scope = "write:books", description = "modify books in your account")
        })
    }, tags={ "book", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = ModelApiResponse.class) })
    public Response uploadFile(@ApiParam(value = "ID of book to update",required=true) @PathParam("bookId") Long bookId
,@ApiParam(value = "Additional data to pass to server")@FormDataParam("additionalMetadata")  String additionalMetadata
,
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.uploadFile(bookId,additionalMetadata,fileInputStream, fileDetail,securityContext);
    }
    @GET
    @Path("/showBooks")

    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Show books", notes = "", response = Book.class, responseContainer = "List", tags={ "book", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Book.class, responseContainer = "List") })
    public Response showBooks(@ApiParam(value = "Show books" ,required=true) @QueryParam("login")String login  ,@Context SecurityContext securityContext)      throws NotFoundException {          
    	return delegate.showBooks(login,securityContext);      
    	}
}
