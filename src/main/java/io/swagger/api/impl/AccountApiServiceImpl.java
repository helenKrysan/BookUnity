package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Account;
import ua.bookUnity.dao.AccountDAO;
import ua.bookUnity.dao.impl.AccountDAOImpl;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-04-24T18:42:49.417Z")
public class AccountApiServiceImpl extends AccountApiService {
    @Override
    public Response createAccount(Account body, SecurityContext securityContext) throws NotFoundException {
        AccountDAO accountDAO = new AccountDAOImpl();
        ua.bookUnity.model.Account acc = accountDAO.save(body.getLogin(), body.getPassword(), "", body.getFirstName(), body.getSecondName(), body.getLastName(), body.getAge(), body.getPhone(), body.getCity(), body.getRegion(), body.getCountry());
        
        if(acc!=null) {
        	 return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Account is created!!!")).build();
        }
        return Response.status(Status.CONFLICT).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Account can't be created!!!")).build();
    }
    @Override
    public Response deleteAccount(String login, SecurityContext securityContext) throws NotFoundException {
    	AccountDAO accountDAO = new AccountDAOImpl();
    	accountDAO.delete(login);
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Account is deleted!!!")).build();
    }
    @Override
    public Response loginIntoAccount( @NotNull String login,  @NotNull String password, SecurityContext securityContext) throws NotFoundException {
    	 AccountDAO accountDAO = new AccountDAOImpl();
    	 ua.bookUnity.model.Account acc = accountDAO.getOneByLogin(login, password);
    	 if(acc!=null) {
        	 return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "You are welcome!!!")).build();
        }
        return Response.status(Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Error entering into account!!!")).build();
    }
    @Override
    public Response logoutFromAccount(SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response updateAccount(String login, Account body, SecurityContext securityContext) throws NotFoundException {
    	AccountDAO accountDAO = new AccountDAOImpl();
        ua.bookUnity.model.Account acc = accountDAO.update(login, body.getPassword(), "", body.getFirstName(), body.getSecondName(), body.getLastName(), body.getAge(), body.getPhone(), body.getCity(), body.getRegion(), body.getCountry());
        
        if(acc!=null) {
        	 return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Account is updated!!!")).build();
        }
        return Response.status(Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Account can't be updated!!!")).build();
        
    }
}
