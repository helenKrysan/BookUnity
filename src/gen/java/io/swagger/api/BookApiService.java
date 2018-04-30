package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Book;
import java.io.File;
import io.swagger.model.ModelApiResponse;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-04-24T18:42:49.417Z")
public abstract class BookApiService {
    public abstract Response addBook(Book body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteBook(Long bookId,String apiKey,SecurityContext securityContext) throws NotFoundException;
    public abstract Response findBook( @NotNull String category, List<String> genre, String author, Integer year,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getBookById(Long bookId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateBook(Book body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response uploadFile(Long bookId,String additionalMetadata,InputStream fileInputStream, FormDataContentDisposition fileDetail,SecurityContext securityContext) throws NotFoundException;
    public abstract Response showBooks(String login,SecurityContext securityContext) throws NotFoundException;
}
