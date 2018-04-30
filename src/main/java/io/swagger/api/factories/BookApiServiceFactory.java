package io.swagger.api.factories;

import io.swagger.api.BookApiService;
import io.swagger.api.impl.BookApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-04-24T18:42:49.417Z")
public class BookApiServiceFactory {
    private final static BookApiService service = new BookApiServiceImpl();

    public static BookApiService getBookApi() {
        return service;
    }
}
