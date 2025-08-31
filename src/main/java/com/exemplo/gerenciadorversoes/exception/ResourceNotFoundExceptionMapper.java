package com.exemplo.gerenciadorversoes.exception;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

/**
 * Mapper global para ResourceNotFoundException
 */
@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {

    private static final Logger LOG = Logger.getLogger(ResourceNotFoundExceptionMapper.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ResourceNotFoundException exception) {
        LOG.warnf("Recurso não encontrado: %s", exception.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
            exception.getMessage(),
            "RESOURCE_NOT_FOUND",
            Response.Status.NOT_FOUND.getStatusCode(),
            uriInfo.getPath()
        );

        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .build();
    }
}
