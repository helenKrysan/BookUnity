package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.MassageApiService;
import io.swagger.api.factories.MassageApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Massage;

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

@Path("/massage")


@io.swagger.annotations.Api(description = "the massage API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-04-24T18:42:49.417Z")
public class MassageApi  {
   private final MassageApiService delegate;

   public MassageApi(@Context ServletConfig servletContext) {
      MassageApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("MassageApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (MassageApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = MassageApiServiceFactory.getMassageApi();
      }

      this.delegate = delegate;
   }

    @GET
    @Path("/showMassage")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create massage", notes = "", response = Massage.class, responseContainer = "List", tags={ "massage", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Massage.class, responseContainer = "List") })
    public Response recieveMassage(@ApiParam(value = "Created massage" ,required=true) Massage body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.recieveMassage(body,securityContext);
    }
    @POST
    @Path("/sendMassage")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create massage", notes = "", response = Void.class, tags={ "massage", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class) })
    public Response sendMassage(@ApiParam(value = "Created massage" ,required=true) Massage body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.sendMassage(body,securityContext);
    }
}
