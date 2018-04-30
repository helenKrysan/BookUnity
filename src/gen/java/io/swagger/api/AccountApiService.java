package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Account;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-04-24T18:42:49.417Z")
public abstract class AccountApiService {
    public abstract Response createAccount(Account body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteAccount(String login,SecurityContext securityContext) throws NotFoundException;
    public abstract Response loginIntoAccount( @NotNull String login, @NotNull String password,SecurityContext securityContext) throws NotFoundException;
    public abstract Response logoutFromAccount(SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateAccount(String login,Account body,SecurityContext securityContext) throws NotFoundException;
}
