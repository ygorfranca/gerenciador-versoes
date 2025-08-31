package com.exemplo.gerenciadorversoes.exception;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

/**
 * Mapper global para BusinessRuleException
 */
@Provider
public class BusinessRuleExceptionMapper implements ExceptionMapper<BusinessRuleException> {

    private static final Logger LOG = Logger.getLogger(BusinessRuleExceptionMapper.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(BusinessRuleException exception) {
        LOG.warnf("Violação de regra de negócio: %s", exception.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
            exception.getMessage(),
            "BUSINESS_RULE_VIOLATION",
            Response.Status.CONFLICT.getStatusCode(),
            uriInfo.getPath()
        );

        return Response.status(Response.Status.CONFLICT)
                .entity(errorResponse)
                .build();
    }
}
