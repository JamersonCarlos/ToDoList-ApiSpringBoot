package api.todo_list_api.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidTokenAccessException extends AuthenticationException {

    public InvalidTokenAccessException(String msg) {
        super(msg);
        
    }
    
}
