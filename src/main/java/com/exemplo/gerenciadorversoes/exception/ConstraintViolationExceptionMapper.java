package com.exemplo.gerenciadorversoes.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper global para ConstraintViolationException (Bean Validation)
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger LOG = Logger.getLogger(ConstraintViolationExceptionMapper.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        LOG.warnf("Erro de validação: %s", exception.getMessage());

        List<ErrorResponse.FieldError> fieldErrors = exception.getConstraintViolations()
                .stream()
                .map(this::createFieldError)
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Dados inválidos fornecidos")
                .error("VALIDATION_FAILED")
                .status(Response.Status.BAD_REQUEST.getStatusCode())
                .path(uriInfo.getPath())
                .fieldErrors(fieldErrors)
                .build();

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .build();
    }

    private ErrorResponse.FieldError createFieldError(ConstraintViolation<?> violation) {
        String fieldName = violation.getPropertyPath().toString();
        Object rejectedValue = violation.getInvalidValue();
        String message = violation.getMessage();
        
        return ErrorResponse.FieldError.builder()
                .field(fieldName)
                .rejectedValue(rejectedValue)
                .message(message)
                .build();
    }
}
