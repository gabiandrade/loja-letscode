package br.com.letscode.lojaletscode.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String entity) {
        super(entity + "not found");
    }

}
