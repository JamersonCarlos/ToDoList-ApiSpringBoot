package api.todo_list_api.Exceptions;

import javax.naming.AuthenticationException;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
