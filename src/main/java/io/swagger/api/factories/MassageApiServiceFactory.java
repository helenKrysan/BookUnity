package io.swagger.api.factories;

import io.swagger.api.MassageApiService;
import io.swagger.api.impl.MassageApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-04-24T18:42:49.417Z")
public class MassageApiServiceFactory {
    private final static MassageApiService service = new MassageApiServiceImpl();

    public static MassageApiService getMassageApi() {
        return service;
    }
}
