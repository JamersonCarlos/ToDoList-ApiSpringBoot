package api.todo_list_api.Exceptions;

public class PasswordLengthInvalidException extends RuntimeException {

    public PasswordLengthInvalidException(String message) {
        super(message);
    }
    
}
