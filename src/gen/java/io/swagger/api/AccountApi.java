package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.AccountApiService;
import io.swagger.api.factories.AccountApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Account;

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

@Path("/account")


@io.swagger.annotations.Api(description = "the account API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-04-24T18:42:49.417Z")
public class AccountApi  {
   private final AccountApiService delegate;

   public AccountApi(@Context ServletConfig servletContext) {
      AccountApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("AccountApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (AccountApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = AccountApiServiceFactory.getAccountApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create user", notes = "", response = Void.class, tags={ "account", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class) })
    public Response createAccount(@ApiParam(value = "Created user object" ,required=true) Account body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createAccount(body,securityContext);
    }
    @DELETE
    @Path("/{login}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete account", notes = "This can only be done by the logged in user.", response = Void.class, tags={ "account", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid login supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found", response = Void.class) })
    public Response deleteAccount(@ApiParam(value = "The account that needs to be deleted",required=true) @PathParam("login") String login
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteAccount(login,securityContext);
    }
    @GET
    @Path("/login")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Logs user into the system", notes = "", response = String.class, tags={ "account", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = String.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid username/password supplied", response = Void.class) })
    public Response loginIntoAccount(@ApiParam(value = "The user name for login",required=true) @QueryParam("login") String login
,@ApiParam(value = "The password for login in clear text",required=true) @QueryParam("password") String password
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.loginIntoAccount(login,password,securityContext);
    }
    @GET
    @Path("/logout")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Logs out current logged in user session", notes = "", response = Void.class, tags={ "account", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class) })
    public Response logoutFromAccount(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.logoutFromAccount(securityContext);
    }
    @PUT
    @Path("/{login}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update account", notes = "", response = Void.class, tags={ "account", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid user supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found", response = Void.class) })
    public Response updateAccount(@ApiParam(value = "name that need to be updated",required=true) @PathParam("login") String login
,@ApiParam(value = "Updated user object" ,required=true) Account body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateAccount(login,body,securityContext);
    }
}
