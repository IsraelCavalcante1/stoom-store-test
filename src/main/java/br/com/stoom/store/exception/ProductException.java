package br.com.stoom.store.exception;

public class ProductException extends RuntimeException {
    private int code;
    private String message;

    public ProductException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{ \"code\": " + code + ", \"message\": \"" + message + "\" }";
    }
}