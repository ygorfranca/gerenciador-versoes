package com.exemplo.gerenciadorversoes.exception;

/**
 * Exceção para violação de regras de negócio
 */
public class BusinessRuleException extends RuntimeException {
    
    private final String code;

    public BusinessRuleException(String message) {
        super(message);
        this.code = null;
    }

    public BusinessRuleException(String message, String code) {
        super(message);
        this.code = code;
    }

    public BusinessRuleException(String message, Throwable cause) {
        super(message, cause);
        this.code = null;
    }

    public String getCode() {
        return code;
    }
}
