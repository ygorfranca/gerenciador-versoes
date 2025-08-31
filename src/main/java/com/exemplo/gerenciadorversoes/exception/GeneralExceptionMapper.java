package com.exemplo.gerenciadorversoes.exception;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

/**
 * Mapper global para exceções genéricas não tratadas
 */
@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(GeneralExceptionMapper.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(Exception exception) {
        LOG.errorf(exception, "Erro interno não tratado: %s", exception.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Erro interno do servidor")
                .error("INTERNAL_SERVER_ERROR")
                .status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                .path(uriInfo.getPath())
                .build();

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .build();
    }
}
